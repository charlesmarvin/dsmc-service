package com.dsmc.api.core.data;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Copyright 2015 Marvin Charles
 */
public class Contact {
    private String id;
    private Date  createdOn;
    private String firstName;
    private String lastName;
    private String email;
    private String primaryPhone;
    private String secondaryPhone;
    private Address address;
    private Gender gender;
    private boolean active;

    public Contact() {}

    public Contact(Document document) {
        this.setId(document.getObjectId("_id").toString());
        this.setFirstName(document.getString("firstName"));
        this.setLastName(document.getString("lastName"));
        this.setCreatedOn(document.getDate("createdOn"));
        this.setEmail(document.getString("email"));
        this.setActive(document.getBoolean("active"));
        this.setGender(Gender.valueOf(document.getString("gender")));
        this.setPrimaryPhone(document.getString("primaryPhone"));
        this.setSecondaryPhone(document.getString("secondaryPhone"));
        Document addressDocument = (Document) document.get("address");
        this.setAddress(new Address(addressDocument));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

    public static Document asDocument(Contact contact) {
        Document document = new Document()
                .append("firstName", contact.getFirstName())
                .append("lastName", contact.getLastName())
                .append("gender", contact.getGender().name())
                .append("createdOn", contact.getCreatedOn())
                .append("email", contact.getEmail())
                .append("primaryPhone", contact.getPrimaryPhone())
                .append("secondaryPhone", contact.getSecondaryPhone())
                .append("active", contact.isActive())
                .append("address", Address.asDocument(contact.getAddress()));
        if (contact.getId() != null && !contact.getId().isEmpty()) {
            document.append("_id", new ObjectId(contact.getId()));
        }
        return document;
    }
}
