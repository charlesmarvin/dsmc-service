package com.dsmc;

import java.util.List;

public class AppConfig {

    private String databaseConnectionUri;
    private List<String> apiClients;
    private List<String> authKeys;

    public String getDatabaseConnectionUri() {
        return databaseConnectionUri;
    }

    public List<String> getApiClients() {
        return apiClients;
    }

    public List<String> getAuthKeys() {
        return authKeys;
    }
}
