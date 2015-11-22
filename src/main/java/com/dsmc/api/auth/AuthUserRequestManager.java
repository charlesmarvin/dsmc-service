package com.dsmc.api.auth;

import com.dsmc.data.tables.pojos.AdminUser;
import spark.Request;

public class AuthUserRequestManager {

    public static final String ATTRIBUTE = "admin_user";

    public static Integer getUserId(Request request) {
        AdminUser adminUser = request.attribute(ATTRIBUTE);
        return adminUser.getCompanyId().intValue();
    }

    public static String getUsername(Request request) {
        AdminUser adminUser = request.attribute(ATTRIBUTE);
        return adminUser.getUsername();
    }

    public static Integer getCompanyId(Request request) {
        AdminUser adminUser = request.attribute(ATTRIBUTE);
        return adminUser.getCompanyId().intValue();
    }

    public static void setAuthUser(Request request, AdminUser adminUser) {
        request.attribute(ATTRIBUTE, adminUser);
    }
}
