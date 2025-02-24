package com.kwizera.spring_boot_blog_api.Services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kwizera.spring_boot_blog_api.Services.CategoryServices;
import com.kwizera.spring_boot_blog_api.Services.PostService;
import com.kwizera.spring_boot_blog_api.Services.TagService;
import com.kwizera.spring_boot_blog_api.domain.CreatePostRequest;
import com.kwizera.spring_boot_blog_api.domain.PostStatus;
import com.kwizera.spring_boot_blog_api.domain.entities.Category;
import com.kwizera.spring_boot_blog_api.domain.entities.Post;
import com.kwizera.spring_boot_blog_api.domain.entities.Tag;
import com.kwizera.spring_boot_blog_api.domain.entities.User;
import com.kwizera.spring_boot_blog_api.repositories.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryServices categoryServices;
    private final TagService tagService;
    private static final int WORDS_PER_MINUTE = 200;

    @Transactional(readOnly = true)
    @Override
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
        if (null != categoryId && null != tagId) {
            Category foundCategory = categoryServices.getCategoryById(categoryId);
            Tag foundTag = tagService.getTagById(tagId);

            return postRepository.findAllByStatusAndCategoryAndTagsContaining(PostStatus.PUBLISHED, foundCategory,
                    foundTag);
        }
        if (categoryId != null) {
            Category foundCategory = categoryServices.getCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED, foundCategory);
        }

        if (tagId != null) {
            Tag foundTag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndTagsContaining(PostStatus.PUBLISHED, foundTag);
        }

        return postRepository.findAllByStatus(PostStatus.PUBLISHED);
    }

    @Override
    public List<Post> getDraftPosts(User user) {
        return postRepository.findAllByAuthorAndStatus(user, PostStatus.DRAFT);
    }

    @Transactional
    @Override
    public Post createPost(User user, CreatePostRequest createPostRequest) {
        Post newPost = new Post();
        newPost.setTitle(createPostRequest.getTitle());
        newPost.setContent(createPostRequest.getContent());
        newPost.setStatus(createPostRequest.getStatus());
        newPost.setAuthor(user);
        newPost.setReadingTime(calculateReadingTime(createPostRequest.getContent()));

        Category category = categoryServices.getCategoryById(createPostRequest.getCategoryId());

        newPost.setCategory(category);

        Set<UUID> tagIds = createPostRequest.getTagIds();

        List<Tag> tags = tagService.getTagByIds(tagIds);

        newPost.setTags(new HashSet<>(tags));

        return postRepository.save(newPost);
    }

    private Integer calculateReadingTime(String content) {
        if (null == content || content.isEmpty())
            return 0;

        int wordcount = content.trim().split("\\s+").length;
        return (int) Math.ceil((double) wordcount / WORDS_PER_MINUTE);
    }

}
