package com.user.audit.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.audit.service.service.RegistrationService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final RegistrationService registrationService;

    public UserController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
	
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestParam String name,
            @RequestParam String email) {

        registrationService.register(name, email);
        return ResponseEntity.ok("User registered successfully");
    }
}