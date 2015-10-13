package com.dsmc.api.instructors;

import com.dsmc.api.common.CrudResource;
import com.dsmc.api.core.transformers.SerializationProvider;

/**
 * Copyright 2015 Marvin Charles
 */
public class InstructorResource extends CrudResource<String, Instructor> {
    public InstructorResource(InstructorService service, SerializationProvider serializationProvider) {
        super(Instructor.class, service, serializationProvider);
    }

    @Override
    public String getPath() {
        return "instructors";
    }
}
