package com.helpdesktech.helpdesk.service.impl;

import com.helpdesktech.helpdesk.dto.auth.LoginDTO;
import com.helpdesktech.helpdesk.entity.User;
import com.helpdesktech.helpdesk.exception.ResourceNotFoundException;
import com.helpdesktech.helpdesk.repository.UserRepository;
import com.helpdesktech.helpdesk.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    @Override
    public String logIn(LoginDTO loginDTO) {
        User login = userRepository.findByEmail(loginDTO.email())
                .orElseThrow(() -> new ResourceNotFoundException("Email not found" + loginDTO.email()));

        if (login.getPassword().equals(loginDTO.password())) {
            return "Passowrd in valid";
        }
        return "Wrong Password";
    }
}
