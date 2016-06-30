package com.dsmc.api.core.filters;

import com.dsmc.api.auth.AuthUserRequestManager;
import com.dsmc.api.auth.UserAuthService;
import com.dsmc.data.tables.pojos.AdminUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.before;
import static spark.Spark.halt;

/**
 * Copyright 2016 Marvin Charles
 */
public class AuthFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CORSFilter.class);

    private static final String AUTHORIZATION_TYPE_PREFIX = "Bearer ";
    private final String authorizationApiContext;
    private final UserAuthService authService;

    public AuthFilter(String authorizationApiContext, UserAuthService authService) {
        this.authorizationApiContext = authorizationApiContext;
        this.authService = authService;
        init();
    }

    private void init() {
        LOGGER.info("Initializing Auth Filter");
        before(authorizationApiContext + "*", (request, response) -> {
            if (request.requestMethod().equals("OPTIONS")) return;
            AdminUser adminUser = null;
            try {
                String authHeader = request.headers("Authorization");
                if (!(StringUtils.isBlank(authHeader)
                        || !authHeader.startsWith(AUTHORIZATION_TYPE_PREFIX))) {
                    throw new RuntimeException("Path requires authorization but no Authorization token present in request header.");
                }
                if (!StringUtils.isBlank(authHeader)) {
                    String token = authHeader.replace(AUTHORIZATION_TYPE_PREFIX, "");
                    adminUser = authService.getAdminUserFromToken(token);
                    if (adminUser == null) {
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
    }
}
