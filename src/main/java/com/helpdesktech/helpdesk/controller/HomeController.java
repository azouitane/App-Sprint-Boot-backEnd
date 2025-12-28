package com.helpdesktech.helpdesk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> home() {
        Map<String, String> response = Map.of(
                "message", "Welcome to HelpDeskTech API",
                "status", "ok"
        );
        return ResponseEntity.ok(response);
    }
}
