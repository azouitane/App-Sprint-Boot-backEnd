package com.helpdesktech.helpdesk.controller;
import com.helpdesktech.helpdesk.dto.auth.LoginRequestDTO;
import com.helpdesktech.helpdesk.dto.auth.LoginResponseDTO;
import com.helpdesktech.helpdesk.service.interfaces.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        String token = authService.authenticate(request);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
