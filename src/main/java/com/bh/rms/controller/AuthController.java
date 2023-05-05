package com.bh.rms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RestController
public class AuthController {

    @RequestMapping("/signin")
    public ResponseEntity<String> login(String userName, String password) {
        return ResponseEntity.ok()
                .header(SET_COOKIE, "")
                .body("");
    }
}
