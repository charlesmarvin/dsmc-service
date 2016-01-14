package com.dsmc.api.students;

import com.dsmc.api.auth.AuthUserRequestManager;
import com.dsmc.api.core.transformers.JsonTransformer;
import com.dsmc.api.core.transformers.SerializationProvider;
import com.dsmc.data.tables.pojos.Student;
import spark.ResponseTransformer;

import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

/**
 * Copyright 2015 Marvin Charles
 */
public class StudentResource {

    private final String context;
    private final StudentService studentService;
    private final SerializationProvider serializationProvider;
    private final ResponseTransformer transformer;

    public StudentResource(String context, StudentService studentService, SerializationProvider serializationProvider) {
        this.context = context;
        this.studentService = studentService;
        this.serializationProvider = serializationProvider;
        this.transformer = new JsonTransformer(serializationProvider);
        configureRoutes();
    }

    private void configureRoutes() {
        get(context + "students", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    return studentService.getStudents(companyId);
                }, transformer
        );

        get(context + "students/:id", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    Integer studentId = Integer.parseInt(request.params("id"));
                    return studentService.getStudentById(companyId, studentId);
                }, transformer
        );

        post(context + "students", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    Student student = serializationProvider.get().fromJson(request.body(), Student.class);
                    studentService.create(companyId, student);
                    response.status(201);
                    return "OK";
                }, transformer
        );

        put(context + "students/:id", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    Integer studentId = Integer.parseInt(request.params("id"));
                    Map<String, ?> updates = serializationProvider.get().fromJson(request.body(), Map.class);
                    studentService.update(companyId, studentId, updates);
                    response.status(201);
                    return "OK";
                }, transformer
        );
    }
}
