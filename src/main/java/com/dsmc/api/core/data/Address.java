package com.dsmc.api.core.data;

import org.bson.Document;

/**
 * Copyright 2015 Marvin Charles
 */
public class Address {
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String zipcode;

    public Address() {
    }

    public Address(Document addressDocument) {
        this.setLine1(addressDocument.getString("line1"));
        this.setLine2(addressDocument.getString("line2"));
        this.setCity(addressDocument.getString("city"));
        this.setState(addressDocument.getString("state"));
        this.setZipcode(addressDocument.getString("zipcode"));
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public static Document asDocument(Address address) {
        return new Document()
                .append("line1", address.getLine1())
                .append("line2", address.getLine2())
                .append("city", address.getCity())
                .append("state", address.getState())
                .append("zipcode", address.getZipcode());
    }
}
