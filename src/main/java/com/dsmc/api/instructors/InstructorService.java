package com.dsmc.api.instructors;

import com.dsmc.api.common.MongoCrudService;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Copyright 2015 Marvin Charles
 */
public class InstructorService extends MongoCrudService<String, Instructor> {
    public InstructorService(MongoDatabase mongo) {
        super(mongo.getCollection("instructors"));
    }

    @Override
    public Instructor toModel(Document document) {
        return new Instructor(document);
    }

    @Override
    public Document toEntity(Instructor instructor) {
        return Instructor.asDocument(instructor);
    }

    @Override
    public String getKey(Instructor o) {
        return o.getId();
    }
}
