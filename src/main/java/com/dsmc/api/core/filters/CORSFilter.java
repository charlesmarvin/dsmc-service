package com.dsmc.api.core.filters;

import com.dsmc.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.after;
import static spark.Spark.options;

/**
 * Copyright 2016 Marvin Charles
 */
public class CORSFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CORSFilter.class);

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


    private final AppConfig appConfig;

    public CORSFilter(AppConfig appConfig) {
        this.appConfig = appConfig;
        init();
    }

    private void init() {
        LOGGER.info("Initializing CORS Filter");
        options("/*", (request, response) -> {
            response.status(200);
            return "OK";
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
}
