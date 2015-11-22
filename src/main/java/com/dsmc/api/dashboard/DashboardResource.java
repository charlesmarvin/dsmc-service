package com.dsmc.api.dashboard;

import com.dsmc.api.auth.AuthUserRequestManager;
import com.dsmc.api.core.transformers.JsonTransformer;
import com.dsmc.api.core.transformers.SerializationProvider;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * Created by charlesmarvin on 10/25/15.
 */
public class DashboardResource {
    private final String context;
    private final DashboardService dashboardService;
    private final SerializationProvider serializationProvider;

    public DashboardResource(String context, DashboardService dashboardService, SerializationProvider serializationProvider) {
        this.context = context;
        this.dashboardService = dashboardService;
        this.serializationProvider = serializationProvider;
        configure();
    }

    protected void configure() {
        get(context + "dashboard", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    Map<String, Object> dashboardData = new HashMap<>(3);
                    dashboardData.put("studentsByGenderCount", dashboardService.getStudentsByGenderCount(companyId));
                    dashboardData.put("studentsByPackageCount", dashboardService.getStudentsByPackageCount(companyId));
                    dashboardData.put("studentsByInstructorCount", dashboardService.getStudentsByInstructorCount(companyId));
                    return dashboardData;
                }, new JsonTransformer(serializationProvider)
        );
    }
}
