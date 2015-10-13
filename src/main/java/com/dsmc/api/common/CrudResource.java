package com.dsmc.api.common;

import com.dsmc.api.core.transformers.JsonTransformer;
import com.dsmc.api.core.transformers.SerializationProvider;
import com.dsmc.api.core.transformers.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.patch;
import static spark.Spark.post;
import static spark.Spark.put;

/**
 * Copyright 2015 Marvin Charles
 */
public abstract class CrudResource<K, T> {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    private static final String APPLICATION_JSON = "application/json";
    private static final String API_CONTEXT = "/api/v1";
    private final Class<T> resourceType;
    private final CrudService<K, T, ?> service;
    private final SerializationProvider serializationProvider;
    private final Serializer serializer;

    protected CrudResource(Class<T> resourceType, CrudService<K, T, ?> service, SerializationProvider serializationProvider) {
        this.resourceType = resourceType;

        this.service = service;
        this.serializationProvider = serializationProvider;
        this.serializer = serializationProvider.get();
        configure();
    }

    public abstract String getPath();

    protected void configure() {

        get(API_CONTEXT + "/" + getPath(), APPLICATION_JSON, (request, response)

                -> service.findAll(), new JsonTransformer(serializationProvider));

        get(API_CONTEXT + "/" + getPath() + "/:id", APPLICATION_JSON, (request, response)

                -> service.findById((K) request.params("id")), new JsonTransformer(serializationProvider));

        post(API_CONTEXT + "/" + getPath(), APPLICATION_JSON, (request, response) -> {
            T resource = serializer.fromJson(request.body(), resourceType);
            service.create(resource);
            response.status(201);
            return response;
        }, new JsonTransformer(serializationProvider));

        put(API_CONTEXT + "/" + getPath() + "/:id", APPLICATION_JSON, (request, response) -> {
            T resource = serializer.fromJson(request.body(), resourceType);
            service.update(resource);
            response.status(204);
            return response;
        }, new JsonTransformer(serializationProvider));

        patch(API_CONTEXT + "/" + getPath() + "/:id", APPLICATION_JSON, (request, response) -> {
            Map patchContent = serializer.fromJson(request.body(), Map.class);
            patchContent.remove("id"); //ignore any ids in the patch content
            T originalResource = service.findById((K) request.params("id"));
            Map originalEntityMap = serializer.fromJson(serializer.toJson(originalResource), Map.class);
            Map patchedResource = new HashMap();
            patchedResource.putAll(originalEntityMap);
            patchedResource.putAll(patchContent);
            T resource = serializer.fromJson(serializer.toJson(patchedResource), resourceType);
            service.update(resource);
            response.status(204);
            return response;
        }, new JsonTransformer(serializationProvider));
    }
}
