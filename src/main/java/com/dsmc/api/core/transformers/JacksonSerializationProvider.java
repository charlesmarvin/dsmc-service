package com.dsmc.api.core.transformers;

import com.dsmc.api.core.filters.CORSFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Copyright 2015 Marvin Charles
 */
public class JacksonSerializationProvider implements SerializationProvider {
    @Override
    public Serializer get() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        return new Serializer() {
            private final Logger LOGGER = LoggerFactory.getLogger(CORSFilter.class);

            @Override
            public String toJson(Object o) {
                try {
                    return mapper.writeValueAsString(o);
                } catch (JsonProcessingException e) {
                    LOGGER.error("Failed to write JSON value");
                    return null;
                }
            }

            @Override
            public <T> T fromJson(String s, Class<T> type) {
                try {
                    return mapper.readValue(s, type);
                } catch (IOException e) {
                    LOGGER.error("Failed to read JSON value");
                    return null;
                }
            }
        };
    }
}
