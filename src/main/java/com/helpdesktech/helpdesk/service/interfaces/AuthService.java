package com.helpdesktech.helpdesk.service.interfaces;

import com.helpdesktech.helpdesk.dto.auth.LoginRequestDTO;

public interface AuthService {
    String authenticate(LoginRequestDTO  loginRequestDTO);
}
