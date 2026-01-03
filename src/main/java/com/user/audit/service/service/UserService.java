package com.user.audit.service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.audit.service.entity.User;
import com.user.audit.service.userrepository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User createUser(String name, String email) {
        User user = new User(name, email);
        return repository.save(user);
    }
}