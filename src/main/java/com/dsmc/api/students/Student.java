package com.dsmc.api.students;

import com.dsmc.api.core.data.Contact;
import com.dsmc.api.core.data.Gender;
import org.bson.Document;

import java.util.Calendar;
import java.util.Date;

/**
 * Copyright 2015 Marvin Charles
 */
public class Student extends Contact {
    private Date dob;

    public Student() {}

    public Student(Document document) {
        super(document);
        this.setDob(document.getDate("dob"));
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public static Document asDocument(Student student) {
        Document document = Contact.asDocument(student);
        document.append("dob", student.getDob());
        return document;
    }
}
