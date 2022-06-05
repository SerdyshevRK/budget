package me.home.controllers;

import me.home.models.api.HealthCheckStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class ApiController {
    @GetMapping(value = "/healthcheck")
    public HealthCheckStatus healthCheck() {
        return new HealthCheckStatus("budget", "running");
    }
}
