package com.dsmc;

import java.util.List;

public class AppConfig {

    private String databaseConnectionUri;
    private List<String> apiClients;

    public String getDatabaseConnectionUri() {
        return databaseConnectionUri;
    }

    public List<String> getApiClients() {
        return apiClients;
    }
}
