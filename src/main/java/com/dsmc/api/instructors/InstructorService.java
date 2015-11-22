package com.dsmc.api.instructors;

import com.dsmc.data.tables.pojos.Instructor;
import org.jooq.DSLContext;

import java.util.List;

public class InstructorService {
    private final DSLContext context;

    public InstructorService(DSLContext context) {
        this.context = context;
    }

    public List<Instructor> getInstructors(Integer companyId){
        com.dsmc.data.tables.Instructor i = com.dsmc.data.tables.Instructor.INSTRUCTOR.as("i");
        return context.select()
                .from(i)
                .where(i.COMPANY_ID.equal(companyId))
                .fetch()
                .into(Instructor.class);
    }
}
