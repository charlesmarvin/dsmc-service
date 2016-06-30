package com.dsmc.api.courses;

import com.dsmc.api.auth.AuthUserRequestManager;
import com.dsmc.api.core.transformers.JsonTransformer;
import com.dsmc.api.core.transformers.SerializationProvider;
import com.dsmc.data.tables.pojos.CoursePackage;
import spark.ResponseTransformer;

import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class CourseResource {
    private final String context;
    private final CourseService service;
    private final SerializationProvider serializationProvider;
    private final ResponseTransformer transformer;

    public CourseResource(String context, CourseService service, SerializationProvider serializationProvider) {
        this.context = context;
        this.service = service;
        this.serializationProvider = serializationProvider;
        transformer = new JsonTransformer(serializationProvider);
        configureRoutes();
    }

    private void configureRoutes() {
        get(context + "courses", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    return service.getCourses(companyId);
                }, transformer
        );

        get(context + "courses/:id", (request, response) -> {
            Integer companyId = AuthUserRequestManager.getCompanyId(request);
            Integer packageId = Integer.parseInt(request.params("id"));
                    return service.getCourseById(companyId, packageId);
                }, transformer
        );

        post(context + "courses", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    CoursePackage coursePackage = serializationProvider.get().fromJson(request.body(), CoursePackage.class);
                    service.create(companyId, coursePackage);
                    response.status(201);
                    return "OK";
                }, transformer
        );

        put(context + "courses/:id", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    Integer coursePackage = Integer.parseInt(request.params("id"));
                    Map<String, ?> updates = serializationProvider.get().fromJson(request.body(), Map.class);
                    service.update(companyId, coursePackage, updates);
                    response.status(201);
                    return "OK";
                }, transformer
        );
    }
}
