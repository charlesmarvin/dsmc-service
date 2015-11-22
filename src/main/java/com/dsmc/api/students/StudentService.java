package com.dsmc.api.students;

import com.dsmc.data.tables.pojos.Student;
import org.jooq.DSLContext;

import java.util.List;

/**
 * Copyright 2015 Marvin Charles
 */
public class StudentService {
    private final DSLContext context;

    public StudentService(DSLContext context) {
        this.context = context;
    }

    public List<Student> getStudents(Integer companyId){
        com.dsmc.data.tables.Student s = com.dsmc.data.tables.Student.STUDENT.as("s");
        return context.select()
                .from(s)
                .where(s.COMPANY_ID.equal(companyId))
                .fetch()
                .into(Student.class);
    }
}
