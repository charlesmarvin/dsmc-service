package com.dsmc.api.students;

import com.dsmc.api.common.CrudResource;
import com.dsmc.api.core.transformers.SerializationProvider;

/**
 * Copyright 2015 Marvin Charles
 */
public class StudentResource extends CrudResource<String, Student> {

    public StudentResource(StudentService studentService, SerializationProvider serializationProvider) {
        super(Student.class, studentService, serializationProvider);
    }

    @Override
    public String getPath() {
        return "students";
    }
}
