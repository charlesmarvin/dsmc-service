package com.dsmc.api.auth;

import com.dsmc.api.core.transformers.JsonTransformer;
import com.dsmc.api.core.transformers.SerializationProvider;
import com.google.gson.JsonObject;

import static spark.Spark.post;

public class UserAuthResource {
    private static final String APPLICATION_JSON = "application/json";

    private final String context;
    private final UserAuthService userAuthService;
    private final SerializationProvider serializationProvider;

    public UserAuthResource(String context, UserAuthService service, SerializationProvider serializationProvider) {
        this.context = context;
        this.userAuthService = service;
        this.serializationProvider = serializationProvider;
        configure();
    }

    protected void configure() {
        post(context + "login", APPLICATION_JSON, (request, response) -> {
                    UserLogin userLogin = serializationProvider.get().fromJson(request.body(), UserLogin.class);
                    if (userAuthService.authenticate(userLogin.getUsername(), userLogin.getPassword())) {
                        String jwt = userAuthService.getJsonWebToken(userLogin.getUsername());
                        JsonObject payload = new JsonObject();
                        payload.addProperty("jwt", jwt);
                        payload.addProperty("username", userLogin.getUsername());
                        response.body(payload.toString());
                        response.status(200);
                    } else {
                        response.status(401);
                    }
                    return response;
                }, new JsonTransformer(serializationProvider)
        );

        post(context + "credentials", APPLICATION_JSON, (request, response) -> {
                    String content = request.body();
                    UserLogin resource = serializationProvider.get().fromJson(content, UserLogin.class);
                    if (userAuthService.createLogin(resource.getUsername(), resource.getPassword())) {
                        response.status(201);
                    } else {
                        response.status(500);
                    }
                    return response;
                }, new JsonTransformer(serializationProvider)
        );
    }

    private static class UserLogin {
        private String username;
        private String password;
        private boolean remember;

        public UserLogin() {

        }

        public boolean isRemember() {
            return remember;
        }

        public void setRemember(boolean remember) {
            this.remember = remember;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


    }
}
