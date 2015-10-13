package com.dsmc.api.packages;

import com.dsmc.api.common.MongoCrudService;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Copyright 2015 Marvin Charles
 */
public class PackageService extends MongoCrudService<String, Package>{
    public PackageService(MongoDatabase mongo) {
        super(mongo.getCollection("packages"));
    }

    @Override
    public Package toModel(Document document) {
        return new Package(document);
    }

    @Override
    public Document toEntity(Package o) {
        return Package.asDocument(o);
    }

    @Override
    public String getKey(Package o) {
        return o.getId();
    }
}
