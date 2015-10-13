package com.dsmc.api.packages;

import com.dsmc.api.common.CrudResource;
import com.dsmc.api.core.transformers.SerializationProvider;

/**
 * Copyright 2015 Marvin Charles
 */
public class PackageResource extends CrudResource<String, Package> {
    public PackageResource(PackageService service, SerializationProvider serializationProvider) {
        super(Package.class, service, serializationProvider);
    }

    @Override
    public String getPath() {
        return "packages";
    }
}
