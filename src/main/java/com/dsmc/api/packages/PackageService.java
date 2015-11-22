package com.dsmc.api.packages;

import com.dsmc.data.tables.pojos.CoursePackage;
import org.jooq.DSLContext;

import java.util.List;

public class PackageService {

    private final DSLContext context;

    public PackageService(DSLContext context) {
        this.context = context;
    }

    public List<CoursePackage> getPackages(Integer companyId) {
        com.dsmc.data.tables.CoursePackage c = com.dsmc.data.tables.CoursePackage.COURSE_PACKAGE.as("c");
        return context.select()
                .from(c)
                .where(c.COMPANY_ID.equal(companyId))
                .fetch()
                .into(CoursePackage.class);
    }
}
