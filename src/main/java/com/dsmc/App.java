package com.dsmc;

import com.dsmc.api.auth.AuthUserRequestManager;
import com.dsmc.api.auth.UserAuthResource;
import com.dsmc.api.auth.UserAuthService;
import com.dsmc.api.core.transformers.JacksonSerializationProvider;
import com.dsmc.api.core.transformers.SerializationProvider;
import com.dsmc.api.courses.CourseResource;
import com.dsmc.api.courses.CourseService;
import com.dsmc.api.dashboard.DashboardResource;
import com.dsmc.api.dashboard.DashboardService;
import com.dsmc.api.instructors.InstructorResource;
import com.dsmc.api.instructors.InstructorService;
import com.dsmc.api.packages.PackageResource;
import com.dsmc.api.packages.PackageService;
import com.dsmc.api.sessions.SessionResource;
import com.dsmc.api.sessions.SessionService;
import com.dsmc.api.students.StudentResource;
import com.dsmc.api.students.StudentService;
import com.dsmc.data.tables.pojos.AdminUser;
import org.apache.commons.lang3.StringUtils;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.halt;
import static spark.Spark.options;
import static spark.Spark.port;

/**
 * Copyright 2015 Marvin Charles
 */
public class App {
    private static final String AUTHORIZATION_TYPE_PREFIX = "Bearer ";
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final int PORT = System.getenv("CF_INSTANCE_PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 8080;
    private static final String API_CONTEXT = "/api/";
    private static final String AUTHORIZATION_API_CONTEXT = API_CONTEXT + "auth/";
    private static final Map<String, String> ACCESS_CONTROL_HEADERS = new HashMap<>();
    private static final Map<String, String> CACHE_CONTROL_HEADERS = new HashMap<>();
    private static AppConfig appConfig;

    static {
        ACCESS_CONTROL_HEADERS.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        ACCESS_CONTROL_HEADERS.put("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
        ACCESS_CONTROL_HEADERS.put("Access-Control-Allow-Credentials", "true");

        CACHE_CONTROL_HEADERS.put("Cache-Control", "private, no-store, no-cache, must-revalidate");
        CACHE_CONTROL_HEADERS.put("Content-Type", "application/json; charset=utf-8");
        CACHE_CONTROL_HEADERS.put("Pragma", "no-cache");
    }

    public static void main(String[] args) throws Exception {
        port(PORT);
        SerializationProvider serializationProvider = new JacksonSerializationProvider();
        appConfig = serializationProvider.get().fromJson(System.getenv("APP_CONFIG"), AppConfig.class);
        if (appConfig == null) {
            throw new RuntimeException("APP_CONFIG environment is not set.");
        }
        DSLContext context = DSL.using(getDBConnection(), SQLDialect.POSTGRES_9_4);
        new StudentResource(API_CONTEXT, new StudentService(context), serializationProvider);
        new PackageResource(API_CONTEXT, new PackageService(context), serializationProvider);
        new CourseResource(API_CONTEXT, new CourseService(context), serializationProvider);
        new InstructorResource(API_CONTEXT, new InstructorService(context), serializationProvider);
        new DashboardResource(API_CONTEXT, new DashboardService(context), serializationProvider);
        new SessionResource(API_CONTEXT, new SessionService(context), serializationProvider);
        UserAuthService authService = new UserAuthService(context);
        new UserAuthResource(AUTHORIZATION_API_CONTEXT, authService, serializationProvider);

        before((request, response) -> {
            if (request.requestMethod().equals("OPTIONS")) return;
            AdminUser adminUser = null;
            boolean isAuthRequest = isAuthRequest(request);
            try {
                String authHeader = request.headers("Authorization");
                if (!isAuthRequest
                        && (StringUtils.isBlank(authHeader)
                        || !authHeader.startsWith(AUTHORIZATION_TYPE_PREFIX))) {
                    throw new RuntimeException("Path requires authorization but no Authorization token present in request header.");
                }
                if (!StringUtils.isBlank(authHeader)) {
                    String token = authHeader.replace(AUTHORIZATION_TYPE_PREFIX, "");
                    adminUser = authService.getAdminUserFromToken(token);
                    if (isAuthRequest && adminUser == null) {
                        throw new RuntimeException("Could not authenticate from Authorization token.");
                    }
                    AuthUserRequestManager.setAuthUser(request, adminUser);
                }
            } catch (Exception e) {
                String errorMsg = String.format("Could not authenticate request: %s %s", request.requestMethod(), request.uri());
                LOGGER.warn(errorMsg, e);
                halt(401);
            }
            String user = "unknown";
            if (adminUser != null) {
                user = String.format("%d:%d:%s", adminUser.getCompanyId(), adminUser.getId(), adminUser.getUsername());
            }
            LOGGER.debug("Handling: {} {} [user - {}]", request.requestMethod(), request.uri(), user);

        });
        after((request, response) -> {
            ACCESS_CONTROL_HEADERS.forEach(response::header);
            CACHE_CONTROL_HEADERS.forEach(response::header);
            URL origin = new URL(request.headers("Origin"));
            if (appConfig.getApiClients().stream()
                    .anyMatch(client -> origin.getHost().equals(client))) {
                String allowedOrigin = String.format("%s://%s", origin.getProtocol(), origin.getHost());
                if (origin.getPort() != -1) {
                    allowedOrigin = allowedOrigin + ":" + origin.getPort();
                }
                response.header("Access-Control-Allow-Origin", allowedOrigin);
            }
        });
        options("/*", (request, response) -> {
            response.status(200);
            return "OK";
        });
    }

    private static boolean isAuthRequest(Request request) {
        return request.uri().startsWith(AUTHORIZATION_API_CONTEXT);
    }

    private static Connection getDBConnection() throws SQLException, ClassNotFoundException, URISyntaxException {
        Class.forName("org.postgresql.Driver");
        URI dbUri = new URI(appConfig.getDatabaseConnectionUri());
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        return DriverManager.getConnection(dbUrl, username, password);
    }
}
