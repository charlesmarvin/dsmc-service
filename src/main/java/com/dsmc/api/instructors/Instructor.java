package com.dsmc.api.instructors;

import com.dsmc.api.core.data.Contact;
import java.util.Date;
import org.bson.Document;

/**
 * Copyright 2015 Marvin Charles
 */
public class Instructor extends Contact {
    private Date activeSince;
    private Date certificationDate;

    public Instructor() {}

    public Instructor(Document document) {
        super(document);
        this.setActiveSince(document.getDate("activeSince"));
        this.setCertificationDate(document.getDate("certificationDate"));
    }

    public Date getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(Date activeSince) {
        this.activeSince = activeSince;
    }

    public Date getCertificationDate() {
        return certificationDate;
    }

    public void setCertificationDate(Date certificationDate) {
        this.certificationDate = certificationDate;
    }
}
