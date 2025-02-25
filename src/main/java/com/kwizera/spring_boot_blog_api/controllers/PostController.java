package com.kwizera.spring_boot_blog_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kwizera.spring_boot_blog_api.Services.PostService;
import com.kwizera.spring_boot_blog_api.Services.UserService;
import com.kwizera.spring_boot_blog_api.domain.CreatePostRequest;
import com.kwizera.spring_boot_blog_api.domain.UpdatePostRequest;
import com.kwizera.spring_boot_blog_api.domain.Dtos.CreatePostRequestDto;
import com.kwizera.spring_boot_blog_api.domain.Dtos.PostDto;
import com.kwizera.spring_boot_blog_api.domain.Dtos.UpdatePostRequestDto;
import com.kwizera.spring_boot_blog_api.domain.entities.Post;
import com.kwizera.spring_boot_blog_api.domain.entities.User;
import com.kwizera.spring_boot_blog_api.mappers.PostMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostMapper postMapper;
    private final PostService postService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID tagId) {
        List<Post> foundPosts = postService.getAllPosts(categoryId, tagId);

        List<PostDto> posts = foundPosts.stream().map(postMapper::tDto).toList();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable UUID id) {
        Post foundPost = postService.getPost(id);

        return new ResponseEntity<>(postMapper.tDto(foundPost), HttpStatus.OK);
    }

    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userId) {
        User loggeninUser = userService.getUserById(userId);

        List<Post> draftPosts = postService.getDraftPosts(loggeninUser);

        List<PostDto> draftPostDtos = draftPosts.stream().map(postMapper::tDto).toList();

        return new ResponseEntity<>(draftPostDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody CreatePostRequestDto createPostRequestDto,
            @RequestAttribute UUID userId) {
        User loggenIUser = userService.getUserById(userId);
        CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(createPostRequestDto);

        Post createdPost = postService.createPost(loggenIUser, createPostRequest);

        return new ResponseEntity<>(postMapper.tDto(createdPost), HttpStatus.CREATED);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable UUID id,
            @Valid @RequestBody UpdatePostRequestDto updatePostRequestDto) {

        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(updatePostRequestDto);

        Post updatedPost = postService.updatePost(id, updatePostRequest);

        return new ResponseEntity<>(postMapper.tDto(updatedPost), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
