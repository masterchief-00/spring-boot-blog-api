package com.kwizera.spring_boot_blog_api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kwizera.spring_boot_blog_api.domain.PostStatus;
import com.kwizera.spring_boot_blog_api.domain.entities.Category;
import com.kwizera.spring_boot_blog_api.domain.entities.Post;
import com.kwizera.spring_boot_blog_api.domain.entities.Tag;
import com.kwizera.spring_boot_blog_api.domain.entities.User;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);

    List<Post> findAllByStatusAndCategory(PostStatus status, Category category);

    List<Post> findAllByStatusAndTagsContaining(PostStatus postStatus, Tag tag);

    List<Post> findAllByStatus(PostStatus postStatus);

    List<Post> findAllByAuthorAndStatus(User author, PostStatus status);
}
