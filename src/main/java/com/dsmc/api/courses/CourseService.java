package com.dsmc.api.courses;

import com.dsmc.data.tables.pojos.CoursePackage;
import com.dsmc.data.tables.records.CoursePackageRecord;
import com.google.common.base.CaseFormat;
import org.jooq.DSLContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CourseService {
    private final DSLContext context;
    private Collection<String> nonUpdatableFields = Arrays.asList("id", "companyId", "createdOn");

    public CourseService(DSLContext context) {
        this.context = context;
    }

    public List<CoursePackage> getCourses(Integer companyId) {
        com.dsmc.data.tables.CoursePackage c = com.dsmc.data.tables.CoursePackage.COURSE_PACKAGE.as("c");
        return context.select()
                .from(c)
                .where(c.COMPANY_ID.equal(companyId))
                .fetch()
                .into(CoursePackage.class);
    }

    public CoursePackage getCourseById(Integer companyId, Integer packageId) {
        com.dsmc.data.tables.CoursePackage c = com.dsmc.data.tables.CoursePackage.COURSE_PACKAGE.as("c");
        return context.select()
                .from(c)
                .where(c.COMPANY_ID.equal(companyId).and(c.ID.equal(packageId)))
                .fetchOne()
                .into(CoursePackage.class);
    }

    public void create(Integer companyId, CoursePackage coursePackage) {
        CoursePackageRecord coursePackageRecord = context.newRecord(com.dsmc.data.tables.CoursePackage.COURSE_PACKAGE, coursePackage);
        coursePackageRecord.setCompanyId(companyId);
        coursePackageRecord.setActive("Y");
        coursePackageRecord.store();
    }

    public void update(Integer companyId, Integer coursePackageId, Map<String, ?> updates) {
        com.dsmc.data.tables.CoursePackage s = com.dsmc.data.tables.CoursePackage.COURSE_PACKAGE.as("s");
        CoursePackageRecord coursePackageRecord = context.selectFrom(s)
                .where(s.COMPANY_ID.equal(companyId).and(s.ID.equal(coursePackageId)))
                .fetchOne();
        if (coursePackageRecord == null) {
            throw new RuntimeException("Attempt to update unknown record");
        }
        Map<String, Object> recordUpdates = updates.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .filter(entry -> !nonUpdatableFields.contains(entry.getKey()))
                .collect(Collectors.toMap(entry -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entry.getKey()), Map.Entry::getValue));
        coursePackageRecord.fromMap(recordUpdates);
        coursePackageRecord.store();
    }
}
