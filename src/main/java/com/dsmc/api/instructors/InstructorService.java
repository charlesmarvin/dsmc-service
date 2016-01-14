package com.dsmc.api.instructors;

import com.dsmc.data.tables.pojos.Instructor;
import com.dsmc.data.tables.records.InstructorRecord;
import com.google.common.base.CaseFormat;
import org.jooq.DSLContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InstructorService {
    private final DSLContext context;
    private Collection<String> nonUpdatableFields = Arrays.asList("id", "companyId", "createdOn");


    public InstructorService(DSLContext context) {
        this.context = context;
    }

    public List<Instructor> getInstructors(Integer companyId) {
        com.dsmc.data.tables.Instructor i = com.dsmc.data.tables.Instructor.INSTRUCTOR.as("i");
        return context.select()
                .from(i)
                .where(i.COMPANY_ID.equal(companyId))
                .fetch()
                .into(Instructor.class);
    }

    public Instructor getInstructorById(Integer companyId, Integer instructorId) {
        com.dsmc.data.tables.Instructor i = com.dsmc.data.tables.Instructor.INSTRUCTOR.as("i");
        return context.select()
                .from(i)
                .where(i.COMPANY_ID.equal(companyId).and(i.ID.equal(instructorId)))
                .fetchOne()
                .into(Instructor.class);
    }

    public void create(Integer companyId, Instructor instructor) {
        InstructorRecord instructorRecord = context.newRecord(com.dsmc.data.tables.Instructor.INSTRUCTOR, instructor);
        instructorRecord.setCompanyId(companyId);
        instructorRecord.setActive("Y");
        instructorRecord.store();
    }

    public void update(Integer companyId, Integer instructorId, Map<String, ?> updates) {
        com.dsmc.data.tables.Instructor s = com.dsmc.data.tables.Instructor.INSTRUCTOR.as("s");
        InstructorRecord instructorRecord = context.selectFrom(s)
                .where(s.COMPANY_ID.equal(companyId).and(s.ID.equal(instructorId)))
                .fetchOne();
        if (instructorRecord == null) {
            throw new RuntimeException("Attempt to update unknown record");
        }
        Map<String, Object> recordUpdates = updates.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .filter(entry -> !nonUpdatableFields.contains(entry.getKey()))
                .collect(Collectors.toMap(entry -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entry.getKey()), Map.Entry::getValue));
        instructorRecord.fromMap(recordUpdates);
        instructorRecord.store();
    }
}
