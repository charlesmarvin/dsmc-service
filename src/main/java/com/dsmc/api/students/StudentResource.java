package com.dsmc.api.students;

import com.dsmc.api.auth.AuthUserRequestManager;
import com.dsmc.api.core.transformers.JsonTransformer;
import com.dsmc.api.core.transformers.SerializationProvider;

import static spark.Spark.get;

/**
 * Copyright 2015 Marvin Charles
 */
public class StudentResource {

    private final String context;
    private final StudentService studentService;
    private final SerializationProvider serializationProvider;

    public StudentResource(String context, StudentService studentService, SerializationProvider serializationProvider) {
        this.context = context;
        this.studentService = studentService;
        this.serializationProvider = serializationProvider;
        configureRoutes();
    }

    private void configureRoutes() {
        get(context + "students", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    return studentService.getStudents(companyId);
                }, new JsonTransformer(serializationProvider)
        );
    }
}
