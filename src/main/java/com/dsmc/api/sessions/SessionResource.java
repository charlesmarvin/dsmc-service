package com.dsmc.api.sessions;

import com.dsmc.api.auth.AuthUserRequestManager;
import com.dsmc.api.core.transformers.JsonTransformer;
import com.dsmc.api.core.transformers.SerializationProvider;
import com.dsmc.data.tables.pojos.InstructionSession;
import spark.ResponseTransformer;

import static spark.Spark.get;
import static spark.Spark.post;

public class SessionResource {
    private final String context;
    private final SessionService service;
    private final SerializationProvider serializationProvider;
    private final ResponseTransformer transformer;

    public SessionResource(String context, SessionService service, SerializationProvider serializationProvider) {
        this.context = context;
        this.service = service;
        this.serializationProvider = serializationProvider;
        transformer = new JsonTransformer(serializationProvider);
        configureRoutes();
    }

    private void configureRoutes() {
        get(context + "instructionSessions", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    return service.getSessions(companyId);
                }, transformer
        );

        get(context + "instructionSessions/:id", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    Integer instructionSessionId = Integer.parseInt(request.params("id"));
                    return service.getInstructionSessionById(companyId, instructionSessionId);
                }, transformer
        );

        post(context + "instructionSessions", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    Integer createdBy = AuthUserRequestManager.getUserId(request);
                    InstructionSession instructionSession = serializationProvider.get().fromJson(request.body(), InstructionSession.class);
                    instructionSession.setCreatedBy(createdBy);
                    service.create(companyId, instructionSession);
                    response.status(201);
                    return "OK";
                }, transformer
        );
    }
}
