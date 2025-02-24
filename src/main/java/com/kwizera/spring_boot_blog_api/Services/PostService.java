package com.kwizera.spring_boot_blog_api.Services;

import java.util.List;
import java.util.UUID;

import com.kwizera.spring_boot_blog_api.domain.CreatePostRequest;
import com.kwizera.spring_boot_blog_api.domain.entities.Post;
import com.kwizera.spring_boot_blog_api.domain.entities.User;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId, UUID tagId);

    List<Post> getDraftPosts(User user);

    Post createPost(User user, CreatePostRequest createPostRequest);
}
