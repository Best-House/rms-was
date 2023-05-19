package com.bh.rms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public boolean healthCheck() {
        return true;
    }

    @GetMapping(path = {""})
    public String main() {
        return "Hello World";
    }
}
