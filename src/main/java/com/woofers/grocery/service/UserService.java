package com.woofers.grocery.service;

import com.woofers.grocery.entity.User;
import com.woofers.grocery.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("CUSTOMER");
        return repository.save(user);
    }
}
