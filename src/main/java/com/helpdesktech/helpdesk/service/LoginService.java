package com.helpdesktech.helpdesk.service;

import com.helpdesktech.helpdesk.dto.auth.LoginDTO;

public interface LoginService {
    String logIn(LoginDTO loginDTO);
}
