package com.bh.rms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public boolean healthCheck() {
        return true;
    }

    @GetMapping(path = {"/", "/index.html"})
    public String main() {
        return "Hello World";
    }
}
