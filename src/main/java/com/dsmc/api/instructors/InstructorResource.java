package com.dsmc.api.instructors;

import com.dsmc.api.auth.AuthUserRequestManager;
import com.dsmc.api.core.transformers.JsonTransformer;
import com.dsmc.api.core.transformers.SerializationProvider;
import com.dsmc.data.tables.pojos.Instructor;

import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class InstructorResource {
    private final String context;
    private final InstructorService service;
    private final SerializationProvider serializationProvider;
    private final JsonTransformer transformer;

    public InstructorResource(String context, InstructorService service, SerializationProvider serializationProvider) {
        this.context = context;
        this.service = service;
        this.serializationProvider = serializationProvider;
        transformer = new JsonTransformer(serializationProvider);
        configureRoutes();
    }

    private void configureRoutes() {
        get(context + "instructors", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    return service.getInstructors(companyId);
                }, transformer
        );

        get(context + "instructors/:id", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    Integer instructorId = Integer.parseInt(request.params("id"));
                    return service.getInstructorById(companyId, instructorId);
                }, transformer
        );

        post(context + "instructors", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    Instructor instructor = serializationProvider.get().fromJson(request.body(), Instructor.class);
                    service.create(companyId, instructor);
                    response.status(201);
                    return "OK";
                }, transformer
        );

        put(context + "instructors/:id", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    Integer instructorId = Integer.parseInt(request.params("id"));
                    Map<String, ?> updates = serializationProvider.get().fromJson(request.body(), Map.class);
                    service.update(companyId, instructorId, updates);
                    response.status(201);
                    return "OK";
                }, transformer
        );
    }


}
