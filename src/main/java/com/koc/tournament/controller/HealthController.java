package com.koc.tournament.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "🎾 KOC Tournament App is Running! 🏆";
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("application", "KOC Tournament");
        status.put("timestamp", java.time.Instant.now().toString());
        return status;
    }

    @GetMapping("/actuator/health")
    public Map<String, String> actuatorHealth() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        return status;
    }
}