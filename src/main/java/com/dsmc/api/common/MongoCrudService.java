package com.dsmc.api.common;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2015 Marvin Charles
 */
public abstract class MongoCrudService<K, T> implements CrudService<K, T, Document> {

    protected final MongoCollection<Document> collection;

    protected MongoCrudService(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public List<T> findAll() {
        final List<T> items = new ArrayList<>();
        try (MongoCursor<Document> cursor = getCollection().find().iterator()){
            while (cursor.hasNext()) {
                items.add(toModel(cursor.next()));
            }
        }
        return items;
    }

    @Override
    public T findById(K id) {
        Document document =  getCollection().find(new Document("_id", new ObjectId(id.toString()))).first();
        if (document == null) {
            return null;
        }
        return toModel(document);
    }

    @Override
    public void create(T o) {
        Document key = new Document("_id", new ObjectId(getKey(o).toString()));
        Document update = new Document("$set", toEntity(o));
        getCollection().updateOne(key, update);
    }

    @Override
    public void update(T o) {
        Document key = new Document("_id", new ObjectId(getKey(o).toString()));
        Document update = new Document("$set", toEntity(o));
        getCollection().updateOne(key, update);
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }
}
