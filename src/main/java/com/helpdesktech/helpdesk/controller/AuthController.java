package com.helpdesktech.helpdesk.controller;

import com.helpdesktech.helpdesk.dto.auth.LoginDTO;
import com.helpdesktech.helpdesk.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.module.ModuleDescriptor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    @PutMapping
    public String login(@Valid @RequestBody LoginDTO loginDTO) {
        return "Login successful";
    }
}
