package com.dsmc.api.core.transformers;

/**
 * Copyright 2015 Marvin Charles
 */
public interface Serializer {
    String toJson(Object o);
    <T> T fromJson(String s, Class<T> type);
}
