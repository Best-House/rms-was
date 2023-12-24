package com.bh.rms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HealthCheckController extends AbstractApiController{
    @GetMapping("/health")
    public boolean healthCheck() {
        return true;
    }
}
