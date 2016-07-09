package com.dsmc;

import com.dsmc.api.auth.AuthSigningKeyResolver;
import com.dsmc.api.auth.UserAuthResource;
import com.dsmc.api.auth.UserAuthService;
import com.dsmc.api.core.filters.AuthFilter;
import com.dsmc.api.core.filters.CORSFilter;
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
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static spark.Spark.port;

/**
 * Copyright 2015 Marvin Charles
 */
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final int PORT = System.getenv("CF_INSTANCE_PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 8080;
    private static final String API_CONTEXT = "/api/";
    private static final String AUTHORIZATION_API_CONTEXT = "/auth/";

    private static AppConfig appConfig;



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
        AuthSigningKeyResolver authSigningResolver = new AuthSigningKeyResolver(appConfig.getAuthKeys());
        UserAuthService authService = new UserAuthService(context, authSigningResolver);
        new UserAuthResource(AUTHORIZATION_API_CONTEXT, authService, serializationProvider);

        new CORSFilter(appConfig);
        new AuthFilter(API_CONTEXT, authService);
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
