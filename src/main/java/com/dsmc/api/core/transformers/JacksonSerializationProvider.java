package com.dsmc.api.core.transformers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * Copyright 2015 Marvin Charles
 */
public class JacksonSerializationProvider implements SerializationProvider {
    @Override
    public Serializer get() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return new Serializer() {
            @Override
            public String toJson(Object o) {
                try {
                    return mapper.writeValueAsString(o);
                } catch (JsonProcessingException e) {
                    return null;
                }
            }

            @Override
            public <T> T fromJson(String s, Class<T> type) {
                try {
                    return mapper.readValue(s, type);
                } catch (IOException e) {
                    return null;
                }
            }
        };
    }
}
