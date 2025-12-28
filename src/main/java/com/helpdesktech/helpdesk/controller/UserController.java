package com.helpdesktech.helpdesk.controller;

import com.helpdesktech.helpdesk.dto.user.UpdateUserDTO;
import com.helpdesktech.helpdesk.dto.user.UserRequestDTO;
import com.helpdesktech.helpdesk.dto.user.UserResponseDTO;
import com.helpdesktech.helpdesk.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Create User One Controller
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUserOne(userRequestDTO));
    }

    // Get All Users Controller
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get User By id Controller
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    // Update User

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        return ResponseEntity.ok(userService.updateUser(id, updateUserDTO));
    }
}
