package com.user.audit.service.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.audit.service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
