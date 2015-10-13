package com.dsmc.api.common;

import java.util.List;

/**
 * Copyright 2015 Marvin Charles
 */
public interface CrudService<K, T, E> {
    List<T> findAll();

    T findById(K id);

    void create(T o);

    void update(T o);

    T toModel(E document);

    E toEntity(T o);

    K getKey(T o);
}
