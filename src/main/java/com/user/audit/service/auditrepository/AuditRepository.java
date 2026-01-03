package com.user.audit.service.auditrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.audit.service.entity.AuditLog;

public interface AuditRepository extends JpaRepository<AuditLog, Long> {

}
