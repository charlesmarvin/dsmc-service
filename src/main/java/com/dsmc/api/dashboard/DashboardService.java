package com.dsmc.api.dashboard;

import com.dsmc.data.tables.CoursePackage;
import com.dsmc.data.tables.Instructor;
import com.dsmc.data.tables.Student;
import com.dsmc.data.tables.StudentEnrollment;
import org.jooq.DSLContext;
import org.jooq.Record2;

import java.util.Map;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.concat;
import static org.jooq.impl.DSL.countDistinct;

public class DashboardService {

    private final DSLContext context;

    public DashboardService(DSLContext context) {
        this.context = context;
    }

    Map<String, Integer> getStudentsByGenderCount(Integer companyId) {
        Student s = Student.STUDENT.as("s");
        return context.select(s.GENDER, countDistinct(s.GENDER))
                .from(s)
                .where(s.COMPANY_ID.equal(companyId))
                .groupBy(s.GENDER)
                .fetch()
                .stream()
                .collect(Collectors.toMap(Record2::value1, Record2::value2));
    }

    Map<String, Integer> getStudentsByPackageCount(Integer companyId) {
        CoursePackage p = CoursePackage.COURSE_PACKAGE.as("p");
        StudentEnrollment se = StudentEnrollment.STUDENT_ENROLLMENT.as("se");
        return context.select(p.NAME, countDistinct(se.STUDENT_ID))
                .from(p.join(se)
                        .on(p.ID.eq(se.COURSE_PACKAGE_ID)
                                .and(p.COMPANY_ID.eq(se.COMPANY_ID))))
                .where(p.COMPANY_ID.equal(companyId))
                .groupBy(p.NAME)
                .fetch()
                .stream()
                .collect(Collectors.toMap(Record2::value1, Record2::value2));
    }

    Map<String, Integer> getStudentsByInstructorCount(Integer companyId) {
        Instructor i = Instructor.INSTRUCTOR.as("i");
        StudentEnrollment se = StudentEnrollment.STUDENT_ENROLLMENT.as("se");
        return context.select(concat(i.FIRST_NAME, concat(" ", i.LAST_NAME)), countDistinct(se.STUDENT_ID))
                .from(i.join(se)
                        .on(i.ID.eq(se.INSTRUCTOR_ID))
                        .and(i.COMPANY_ID.eq(se.COMPANY_ID)))
                .where(i.COMPANY_ID.equal(companyId))
                .groupBy(i.ID)
                .fetch()
                .stream()
                .collect(Collectors.toMap(Record2::value1, Record2::value2));
    }
}
