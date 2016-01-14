package com.dsmc.api.students;

import com.dsmc.data.tables.pojos.Student;
import com.dsmc.data.tables.records.StudentRecord;
import com.google.common.base.CaseFormat;
import org.jooq.DSLContext;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Copyright 2015 Marvin Charles
 */
public class StudentService {
    private final DSLContext context;
    private Collection<String> nonUpdatableFields = Arrays.asList("id", "companyId", "createdOn");

    public StudentService(DSLContext context) {
        this.context = context;
    }

    public List<Student> getStudents(Integer companyId) {
        com.dsmc.data.tables.Student s = com.dsmc.data.tables.Student.STUDENT.as("s");
        return context.select()
                .from(s)
                .where(s.COMPANY_ID.equal(companyId))
                .fetch()
                .into(Student.class);
    }

    public Student getStudentById(Integer companyId, Integer studentId) {
        com.dsmc.data.tables.Student s = com.dsmc.data.tables.Student.STUDENT.as("s");
        return context.select()
                .from(s)
                .where(s.COMPANY_ID.equal(companyId).and(s.ID.equal(studentId)))
                .fetchOne()
                .into(Student.class);
    }

    public void create(Integer companyId, Student student) {
        StudentRecord studentRecord = context.newRecord(com.dsmc.data.tables.Student.STUDENT, student);
        studentRecord.setCompanyId(companyId);
        studentRecord.setDob(new Date(Calendar.getInstance().getTimeInMillis()));
        studentRecord.setActive("Y");
        studentRecord.store();
    }

    public void update(Integer companyId, Integer studentId, Map<String, ?> updates) {
        com.dsmc.data.tables.Student s = com.dsmc.data.tables.Student.STUDENT.as("s");
        StudentRecord studentRecord = context.selectFrom(s)
                .where(s.COMPANY_ID.equal(companyId).and(s.ID.equal(studentId)))
                .fetchOne();
        if (studentRecord == null) {
            throw new RuntimeException("Attempt to update unknown record");
        }
        Map<String, Object> recordUpdates = updates.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .filter(entry -> !nonUpdatableFields.contains(entry.getKey()))
                .collect(Collectors.toMap(entry -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entry.getKey()), Map.Entry::getValue));
        studentRecord.fromMap(recordUpdates);
        studentRecord.store();
    }
}
