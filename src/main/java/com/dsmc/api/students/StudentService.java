package com.dsmc.api.students;

import com.dsmc.api.common.MongoCrudService;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Copyright 2015 Marvin Charles
 */
public class StudentService extends MongoCrudService<String, Student> {

    public StudentService(MongoDatabase mongo) {
        super(mongo.getCollection("students"));
    }

    @Override
    public Student toModel(Document document) {
        return new Student(document);
    }

    @Override
    public Document toEntity(Student student) {
        return Student.asDocument(student);
    }

    @Override
    public String getKey(Student o) {
        return o.getId();
    }
}
