package com.kwizera.spring_boot_blog_api.Services.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kwizera.spring_boot_blog_api.Services.UserService;
import com.kwizera.spring_boot_blog_api.domain.entities.User;
import com.kwizera.spring_boot_blog_api.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

}
