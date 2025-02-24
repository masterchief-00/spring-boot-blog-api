package com.kwizera.spring_boot_blog_api.Services;

import java.util.UUID;

import com.kwizera.spring_boot_blog_api.domain.entities.User;

public interface UserService {
    User getUserById(UUID id);
}
