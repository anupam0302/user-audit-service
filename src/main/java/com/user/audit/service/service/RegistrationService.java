package com.user.audit.service.service;

import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserService userService;
    private final AuditService auditService;

    public RegistrationService(UserService userService, AuditService auditService) {
        this.userService = userService;
        this.auditService = auditService;
    }

    public void register(String name, String email) {

        userService.createUser(name, email);
        auditService.log("User registered: " + email);
    }
}