package com.user.audit.service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.audit.service.auditrepository.AuditRepository;
import com.user.audit.service.entity.AuditLog;

@Service
public class AuditService {

    private final AuditRepository repository;

    public AuditService(AuditRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void log(String action) {
        AuditLog log = new AuditLog(action);
        repository.save(log);
    }
}