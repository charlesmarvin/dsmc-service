package com.dsmc;

import com.dsmc.api.core.transformers.JacksonSerializationProvider;
import com.dsmc.api.core.transformers.SerializationProvider;
import com.dsmc.api.instructors.InstructorResource;
import com.dsmc.api.instructors.InstructorService;
import com.dsmc.api.packages.PackageResource;
import com.dsmc.api.packages.PackageService;
import com.dsmc.api.students.StudentResource;
import com.dsmc.api.students.StudentService;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.ipAddress;
import static spark.Spark.port;
/**
 * Copyright 2015 Marvin Charles
 */
public class App
{
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
    private static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8080;

    public static void main(String[] args) throws Exception {
        ipAddress(IP_ADDRESS);
        port(PORT);
        SerializationProvider serializationProvider = new JacksonSerializationProvider();
        MongoDatabase connection = mongo();
        new StudentResource(new StudentService(connection), serializationProvider);
        new PackageResource(new PackageService(connection), serializationProvider);
        new InstructorResource(new InstructorService(connection), serializationProvider);

        before((request, response) -> LOGGER.info("Handling: {} {}", request.requestMethod(), request.uri()));
        after((req, res) -> {
            res.header("Cache-Control", "private, no-store, no-cache, must-revalidate");
            res.header("Pragma", "no-cache");
        });
    }

    private static MongoDatabase mongo() throws Exception {
        
        if (IP_ADDRESS.equals("localhost")) {
            MongoClient mongoClient = new MongoClient("localhost");
            return mongoClient.getDatabase("dsmc");
        }
        String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
        int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
        String database = System.getenv("OPENSHIFT_APP_NAME");
        String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
        MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), Collections.singletonList(credential));
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        return mongoClient.getDatabase(database);
    }
}
