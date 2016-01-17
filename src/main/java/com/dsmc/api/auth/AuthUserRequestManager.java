package com.dsmc.api.auth;

import com.dsmc.data.tables.pojos.AdminUser;
import spark.Request;

public class AuthUserRequestManager {

    public static final String ADMIN_USER_ATTRIBUTE_KEY = "admin_user";

    public static Integer getUserId(Request request) {
        AdminUser adminUser = request.attribute(ADMIN_USER_ATTRIBUTE_KEY);
        return adminUser.getCompanyId();
    }

    public static String getUsername(Request request) {
        AdminUser adminUser = request.attribute(ADMIN_USER_ATTRIBUTE_KEY);
        return adminUser.getUsername();
    }

    public static Integer getCompanyId(Request request) {
        AdminUser adminUser = request.attribute(ADMIN_USER_ATTRIBUTE_KEY);
        return adminUser.getCompanyId();
    }

    public static void setAuthUser(Request request, AdminUser adminUser) {
        request.attribute(ADMIN_USER_ATTRIBUTE_KEY, adminUser);
    }

    public static AdminUser getAuthUser(Request request) {
        return request.attribute(ADMIN_USER_ATTRIBUTE_KEY);
    }

    public static boolean hasAuthUser(Request request) {
        AdminUser adminUser = getAuthUser(request);
        return adminUser != null;
    }
}
