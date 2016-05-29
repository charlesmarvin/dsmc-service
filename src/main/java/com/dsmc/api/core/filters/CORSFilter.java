package com.dsmc.api.core.filters;

import com.dsmc.AppConfig;
import com.dsmc.api.auth.AuthUserRequestManager;
import com.dsmc.api.auth.UserAuthService;
import com.dsmc.data.tables.pojos.AdminUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.halt;
import static spark.Spark.options;

/**
 * Copyright 2015 Marvin Charles
 */
public class CORSFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CORSFilter.class);
    private static final String AUTHORIZATION_TYPE_PREFIX = "Bearer ";

    private static final Map<String, String> ACCESS_CONTROL_HEADERS = new HashMap<>();
    private static final Map<String, String> CACHE_CONTROL_HEADERS = new HashMap<>();

    static {
        ACCESS_CONTROL_HEADERS.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        ACCESS_CONTROL_HEADERS.put("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
        ACCESS_CONTROL_HEADERS.put("Access-Control-Allow-Credentials", "true");

        CACHE_CONTROL_HEADERS.put("Cache-Control", "private, no-store, no-cache, must-revalidate");
        CACHE_CONTROL_HEADERS.put("Content-Type", "application/json; charset=utf-8");
        CACHE_CONTROL_HEADERS.put("Pragma", "no-cache");
    }

    private final String authorizationApiContext;
    private final UserAuthService authService;
    private final AppConfig appConfig;

    public CORSFilter(AppConfig appConfig, String authorizationApiContext, UserAuthService authService) {
        this.appConfig = appConfig;
        this.authorizationApiContext = authorizationApiContext;
        this.authService = authService;
        init();
    }

    private void init() {
        options("/*", (request, response) -> {
            response.status(200);
            return "OK";
        });

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
            appConfig.getApiClients()
                    .stream()
                    .filter(client -> origin.getHost().equals(client))
                    .findFirst()
                    .ifPresent(host -> {
                        String allowedOrigin = String.format("%s://%s", origin.getProtocol(), origin.getHost());
                        if (origin.getPort() != -1) {
                            allowedOrigin = allowedOrigin + ":" + origin.getPort();
                        }
                        response.header("Access-Control-Allow-Origin", allowedOrigin);
                    });
        });
    }

    private boolean isAuthRequest(Request request) {
        return request.uri().startsWith(authorizationApiContext);
    }
}
