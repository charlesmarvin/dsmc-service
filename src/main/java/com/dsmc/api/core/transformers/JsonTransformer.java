package com.dsmc.api.core.transformers;

import spark.ResponseTransformer;

/**
 * Copyright 2015 Marvin Charles
 */
public class JsonTransformer implements ResponseTransformer {
    private final Serializer objectSerializer;

    public JsonTransformer(SerializationProvider serializationProvider) {
        objectSerializer = serializationProvider.get();
    }


    @Override
    public String render(Object model) {
        return objectSerializer.toJson(model);
    }

}