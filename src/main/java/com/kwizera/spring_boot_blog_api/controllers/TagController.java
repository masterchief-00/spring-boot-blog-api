package com.kwizera.spring_boot_blog_api.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kwizera.spring_boot_blog_api.Services.TagService;
import com.kwizera.spring_boot_blog_api.domain.Dtos.CreateTagsRequest;
import com.kwizera.spring_boot_blog_api.domain.Dtos.TagDto;
import com.kwizera.spring_boot_blog_api.domain.entities.Tag;
import com.kwizera.spring_boot_blog_api.mappers.TagMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTags() {
        List<Tag> tags = tagService.getTags();

        List<TagDto> tagResponses = tags.stream().map(tagMapper::toTagResponse).toList();

        return new ResponseEntity<>(tagResponses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<TagDto>> createTags(@RequestBody CreateTagsRequest createTagsRequest) {
        List<Tag> savedTags = tagService.createTags(createTagsRequest.getNames());

        List<TagDto> createdTagResponses = savedTags.stream().map(tagMapper::toTagResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(createdTagResponses, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
        tagService.deleteTag(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
