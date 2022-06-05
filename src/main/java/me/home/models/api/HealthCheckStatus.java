package me.home.models.api;

public class HealthCheckStatus {
    private final String service;
    private final String status;

    public HealthCheckStatus(String service, String status) {
        this.service = service;
        this.status = status;
    }

    public String getService() {
        return service;
    }

    public String getStatus() {
        return status;
    }
}
