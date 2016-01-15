package com.dsmc;

import com.dsmc.api.auth.AuthUserRequestManager;
import com.dsmc.api.auth.UserAuthResource;
import com.dsmc.api.auth.UserAuthService;
import com.dsmc.api.core.transformers.JacksonSerializationProvider;
import com.dsmc.api.core.transformers.SerializationProvider;
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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;

import java.net.URI;
import java.net.URISyntaxException;
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
    public static final String AUTHORIZATION_TYPE_PREFIX = "Bearer ";
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final int PORT = System.getenv("CF_INSTANCE_PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 8080;
    private static final String DB_SERVICE_NAME = "dsmc-pgdb";
    private static final String DB_SERVICE_PROVIDER = "elephantsql";
    private static final String API_CONTEXT = "/api/";
    private static final String AUTHORIZATION_API_CONTEXT = API_CONTEXT + "auth/";
    private static final Map<String, String> CORS_HEADERS = new HashMap<>();

    static {
        CORS_HEADERS.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        CORS_HEADERS.put("Access-Control-Allow-Origin", "*");
        CORS_HEADERS.put("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
        CORS_HEADERS.put("Access-Control-Allow-Credentials", "true");
    }
    public static void main(String[] args) throws Exception {
        port(PORT);
        SerializationProvider serializationProvider = new JacksonSerializationProvider();
        DSLContext context = DSL.using(getDBConnection(), SQLDialect.POSTGRES_9_4);
        new StudentResource(API_CONTEXT, new StudentService(context), serializationProvider);
        new PackageResource(API_CONTEXT, new PackageService(context), serializationProvider);
        new InstructorResource(API_CONTEXT, new InstructorService(context), serializationProvider);
        new DashboardResource(API_CONTEXT, new DashboardService(context), serializationProvider);
        new SessionResource(API_CONTEXT, new SessionService(context), serializationProvider);
        UserAuthService authService = new UserAuthService(context);
        new UserAuthResource(AUTHORIZATION_API_CONTEXT, authService, serializationProvider);

        before((request, response) -> {
            LOGGER.debug("Handling: {} {}", request.requestMethod(), request.uri());
            if (request.requestMethod().equals("OPTIONS")) return;
            boolean isAuthRequest = isAuthRequest(request);
            try {
                String authHeader = request.headers("Authorization");
                if (!isAuthRequest
                        && (StringUtils.isBlank(authHeader)
                        || !authHeader.startsWith(AUTHORIZATION_TYPE_PREFIX))) {
                    halt(401);
                    return;
                }
                if (!isAuthRequest) {
                    String token = authHeader.replace(AUTHORIZATION_TYPE_PREFIX, "");
                    AdminUser adminUser = authService.getAdminUserFromToken(token);
                    if (adminUser == null) {
                        halt(401);
                        return;
                    }
                    AuthUserRequestManager.setAuthUser(request, adminUser);
                }
            } catch (Exception e) {
                LOGGER.error("Could not authenticate request", e);
                halt(401);
            }
        });
        after((request, response) -> {
            CORS_HEADERS.forEach(response::header);
            response.header("Cache-Control", "private, no-store, no-cache, must-revalidate");
            response.header("Content-Type", "application/json; charset=utf-8");
            response.header("Pragma", "no-cache");
        });
        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            response.status(200);
            return "OK";
        });
    }

    private static boolean isAuthRequest(Request request) {
        return request.uri().startsWith(AUTHORIZATION_API_CONTEXT);
    }
    private static Connection getDBConnection() throws SQLException, ClassNotFoundException, URISyntaxException {
        Class.forName("org.postgresql.Driver");
        URI dbUri = new URI(getConnectionUri());
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        return DriverManager.getConnection(dbUrl, username, password);
    }

    private static String getConnectionUri() {
        try {
            JsonParser parser = new JsonParser();
            JsonObject cfg = (JsonObject) parser.parse(System.getenv("VCAP_SERVICES"));
            for (JsonElement conn : cfg.getAsJsonArray(DB_SERVICE_PROVIDER)) {
                if (conn instanceof JsonObject) {
                    JsonObject obj = (JsonObject) conn;
                    if (DB_SERVICE_NAME.equals(obj.get("name").getAsString())) {
                        return ((JsonObject) obj.get("credentials")).get("uri").getAsString();
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error getting database connection info.", e);
        }
        throw new RuntimeException("Database service not configured");
    }
}
