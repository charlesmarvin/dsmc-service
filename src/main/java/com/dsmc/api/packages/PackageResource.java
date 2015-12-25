package com.dsmc.api.packages;

import com.dsmc.api.auth.AuthUserRequestManager;
import com.dsmc.api.core.transformers.JsonTransformer;
import com.dsmc.api.core.transformers.SerializationProvider;
import spark.ResponseTransformer;

import static spark.Spark.get;

public class PackageResource {
    private final String context;
    private final PackageService service;
    private final SerializationProvider serializationProvider;
    private final ResponseTransformer transformer;

    public PackageResource(String context, PackageService service, SerializationProvider serializationProvider) {
        this.context = context;
        this.service = service;
        this.serializationProvider = serializationProvider;
        transformer = new JsonTransformer(serializationProvider);
        configureRoutes();
    }

    private void configureRoutes() {
        get(context + "packages", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    return service.getPackages(companyId);
                }, transformer
        );

        get(context + "packages/:id", (request, response) -> {
            Integer companyId = AuthUserRequestManager.getCompanyId(request);
            Integer packageId = Integer.parseInt(request.params("id"));
                    return service.getPackageById(companyId, packageId);
                }, new JsonTransformer(serializationProvider)
        );
    }
}
