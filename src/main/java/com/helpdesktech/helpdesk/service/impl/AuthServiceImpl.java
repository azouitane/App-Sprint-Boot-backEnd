package com.helpdesktech.helpdesk.service.impl;

import com.helpdesktech.helpdesk.dto.auth.LoginRequestDTO;
import com.helpdesktech.helpdesk.entity.User;
import com.helpdesktech.helpdesk.exception.InvalidCredentialsException;
import com.helpdesktech.helpdesk.repository.UserRepository;
import com.helpdesktech.helpdesk.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String authenticate(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.email())
                .orElseThrow(() -> new InvalidCredentialsException("Email or password is incorrect"));

        if (!passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Email or password is incorrect");
        }

        return jwtService.generateToken(user.getId(), user.getRole().name());
    }

}

