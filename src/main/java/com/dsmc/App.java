package com.dsmc;

import com.dsmc.api.core.transformers.JacksonSerializationProvider;
import com.dsmc.api.core.transformers.SerializationProvider;
import com.dsmc.api.instructors.InstructorResource;
import com.dsmc.api.instructors.InstructorService;
import com.dsmc.api.packages.PackageResource;
import com.dsmc.api.packages.PackageService;
import com.dsmc.api.students.StudentResource;
import com.dsmc.api.students.StudentService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

/**
 * Copyright 2015 Marvin Charles
 */
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final int PORT = System.getenv("CF_INSTANCE_PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 8080;
    private static final String DB_SERVICE_NAME = "dsmc-db";

    public static void main(String[] args) throws Exception {
        port(PORT);
        SerializationProvider serializationProvider = new JacksonSerializationProvider();
        MongoDatabase connection = mongo();
        new StudentResource(new StudentService(connection), serializationProvider);
        new PackageResource(new PackageService(connection), serializationProvider);
        new InstructorResource(new InstructorService(connection), serializationProvider);

        //enable CORS
        before((request, response) -> {
            //TODO make this more restrictive
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "*");
        });

        //add request logging
        before((request, response) -> LOGGER.debug("Handling: {} {}", request.requestMethod(), request.uri()));

        //caching headers
        after((req, res) -> {
            res.header("Cache-Control", "private, no-store, no-cache, must-revalidate");
            res.header("Pragma", "no-cache");
        });
    }

    private static MongoDatabase mongo() throws Exception {
        MongoClientURI clientURI = new MongoClientURI(getConnectionUri());
        MongoClient mongoClient = new MongoClient(clientURI);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        return mongoClient.getDatabase(clientURI.getDatabase());
    }

    private static String getConnectionUri() {
        try {
            JsonParser parser = new JsonParser();
            JsonObject cfg = (JsonObject) parser.parse(System.getenv("VCAP_SERVICES"));
            for (JsonElement conn : cfg.getAsJsonArray("mongolab")) {
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
        throw new RuntimeException("Database service not configured.");
    }
}
