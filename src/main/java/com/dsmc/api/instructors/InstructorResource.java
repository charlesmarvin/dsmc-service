package com.dsmc.api.instructors;

import com.dsmc.api.auth.AuthUserRequestManager;
import com.dsmc.api.core.transformers.JsonTransformer;
import com.dsmc.api.core.transformers.SerializationProvider;

import static spark.Spark.get;

public class InstructorResource {
    private final String context;
    private final InstructorService service;
    private final SerializationProvider serializationProvider;

    public InstructorResource(String context, InstructorService service, SerializationProvider serializationProvider) {
        this.context = context;
        this.service = service;
        this.serializationProvider = serializationProvider;
        configureRoutes();
    }

    private void configureRoutes() {
        get(context + "instructors", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    return service.getInstructors(companyId);
                }, new JsonTransformer(serializationProvider)
        );
    }


}
