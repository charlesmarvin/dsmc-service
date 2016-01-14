package com.dsmc.api.dashboard;

import com.dsmc.data.tables.CoursePackage;
import com.dsmc.data.tables.InstructionSession;
import com.dsmc.data.tables.Instructor;
import com.dsmc.data.tables.Student;
import com.dsmc.data.tables.StudentEnrollment;
import org.jooq.DSLContext;
import org.jooq.Record2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
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

    CompletableFuture<Map<String, Integer>> getStudentsByGenderCountAsync(Integer companyId) {
        return CompletableFuture.supplyAsync(() -> getStudentsByGenderCount(companyId));
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

    CompletableFuture<Map<String, Integer>> getStudentsByPackageCountAsync(Integer companyId) {
        return CompletableFuture.supplyAsync(() -> getStudentsByPackageCount(companyId));
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

    CompletableFuture<Map<String, Integer>> getStudentsByInstructorCountAsync(Integer companyId) {
        return CompletableFuture.supplyAsync(() -> getStudentsByInstructorCount(companyId));
    }

    List<Map<String, ?>> getStudents(Integer companyId) {
        Student s = Student.STUDENT.as("s");
        return context.select(s.ID, concat(s.FIRST_NAME, concat(" ", s.LAST_NAME)))
                .from(s)
                .where(s.COMPANY_ID.equal(companyId))
                .fetch()
                .stream()
                .map(r -> {
                    Map<String, Object> rec = new HashMap<>(2);
                    rec.put("id", r.value1());
                    rec.put("fullName", r.value2());
                    return rec;
                })
                .collect(Collectors.toList());
    }

    CompletableFuture<List<Map<String, ?>>> getStudentsAsync(Integer companyId) {
        return CompletableFuture.supplyAsync(() -> getStudents(companyId));
    }

    List<Map<String, ?>> getInstructors(Integer companyId) {
        Instructor s = Instructor.INSTRUCTOR.as("i");
        return context.select(s.ID, concat(s.FIRST_NAME, concat(" ", s.LAST_NAME)))
                .from(s)
                .where(s.COMPANY_ID.equal(companyId))
                .fetch()
                .stream()
                .map(r -> {
                    Map<String, Object> rec = new HashMap<>(2);
                    rec.put("id", r.value1());
                    rec.put("fullName", r.value2());
                    return rec;
                })
                .collect(Collectors.toList());
    }

    CompletableFuture<List<Map<String, ?>>> getInstructorsAsync(Integer companyId) {
        return CompletableFuture.supplyAsync(() -> getInstructors(companyId));
    }

    List<Map<String, ?>> getInstructionSessions(Integer companyId) {
        InstructionSession instructionSession = InstructionSession.INSTRUCTION_SESSION.as("is");
        Student student = Student.STUDENT.as("s");
        Instructor instructor = Instructor.INSTRUCTOR.as("i");
        return context.select(instructionSession.ID,
                student.ID,
                concat(student.FIRST_NAME, concat(" ", student.LAST_NAME)),
                instructor.ID,
                concat(instructor.FIRST_NAME, concat(" ", instructor.LAST_NAME)),
                instructionSession.SESSION_DATETIME
        )
                .from(instructionSession.join(student)
                        .on(instructionSession.STUDENT_ID.eq(student.ID))
                        .and(instructionSession.COMPANY_ID.eq(student.COMPANY_ID))
                        .join(instructor).on(instructionSession.INSTRUCTOR_ID.eq(instructor.ID))
                        .and(instructionSession.COMPANY_ID.eq(instructor.COMPANY_ID)))
                .where(instructionSession.COMPANY_ID.equal(companyId))
                .fetch()
                .stream()
                .map(r -> {
                    Map<String, Object> rec = new HashMap<>(6);
                    rec.put("instructionSessionId", r.value1());
                    rec.put("studentId", r.value2());
                    rec.put("studentFullName", r.value3());
                    rec.put("instructorId", r.value4());
                    rec.put("instructorFullName", r.value5());
                    rec.put("sessionDateTime", r.value6());
                    return rec;
                })
                .collect(Collectors.toList());
    }

    CompletableFuture<List<Map<String, ?>>> getInstructionSessionsAsync(Integer companyId) {
        return CompletableFuture.supplyAsync(() -> getInstructionSessions(companyId));
    }
}
