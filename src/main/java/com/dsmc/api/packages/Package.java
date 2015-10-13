package com.dsmc.api.packages;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Copyright 2015 Marvin Charles
 */
public class Package {
    private String id;
    private Date createdOn;
    private String name;
    private String description;
    private int price;
    private boolean active;

    public Package() {}

    public Package(Document document) {
        this.setId(document.getObjectId("_id").toString());
        this.setName(document.getString("name"));
        this.setDescription(document.getString("description"));
        this.setCreatedOn(document.getDate("createdOn"));
        this.setPrice(document.getInteger("price"));
        this.setActive(document.getBoolean("active"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static Document asDocument(Package pkg) {
        Document document = new Document();
        document.append("name", pkg.getName());
        document.append("description", pkg.getDescription());
        document.append("price", pkg.getPrice());
        document.append("createdOn", pkg.getCreatedOn());
        document.append("active", pkg.isActive());
        if (pkg.getId() != null && !pkg.getId().isEmpty()) {
            document.append("_id", new ObjectId(pkg.getId()));
        }
        return document;
    }
}
