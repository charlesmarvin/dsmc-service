package com.dsmc.api.dashboard;

import com.dsmc.api.auth.AuthUserRequestManager;
import com.dsmc.api.core.transformers.JsonTransformer;
import com.dsmc.api.core.transformers.SerializationProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static spark.Spark.get;

/**
 * Created by charlesmarvin on 10/25/15.
 */
public class DashboardResource {
    private final String context;
    private final DashboardService dashboardService;
    private final JsonTransformer transformer;

    public DashboardResource(String context,
                             DashboardService dashboardService,
                             SerializationProvider serializationProvider) {
        this.context = context;
        this.dashboardService = dashboardService;
        this.transformer = new JsonTransformer(serializationProvider);
        configure();
    }

    protected void configure() {
        get(context + "dashboard", (request, response) -> {
                    Integer companyId = AuthUserRequestManager.getCompanyId(request);
                    Map<String, Object> dashboardData = new HashMap<>(4);
                    CompletableFuture<Void> f1 = dashboardService.getStudentsAsync(companyId)
                            .thenAccept(students -> dashboardData.put("students", students));
                    CompletableFuture<Void> f2 = dashboardService.getStudentsByGenderCountAsync(companyId)
                            .thenAccept(studentsByGenderCount -> dashboardData.put("studentsByGenderCount", studentsByGenderCount));
                    CompletableFuture<Void> f3 = dashboardService.getStudentsByPackageCountAsync(companyId)
                            .thenAccept(studentsByPackageCount -> dashboardData.put("studentsByPackageCount", studentsByPackageCount));
                    CompletableFuture<Void> f4 = dashboardService.getStudentsByInstructorCountAsync(companyId)
                            .thenAccept(studentsByInstructorCount -> dashboardData.put("studentsByInstructorCount", studentsByInstructorCount));
                    CompletableFuture<Void> f5 = dashboardService.getInstructorsAsync(companyId)
                            .thenAccept(instructors -> dashboardData.put("instructors", instructors));
                    CompletableFuture<Void> f6 = dashboardService.getInstructionSessionsAsync(companyId)
                            .thenAccept(instructionSessions -> dashboardData.put("instructionSessions", instructionSessions));
                    CompletableFuture.allOf(f1, f2, f3, f4, f5, f6).get();
                    return dashboardData;
                }, transformer
        );
    }
}
