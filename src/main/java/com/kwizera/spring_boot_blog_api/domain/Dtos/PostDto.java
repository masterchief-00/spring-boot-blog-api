package com.kwizera.spring_boot_blog_api.domain.Dtos;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.kwizera.spring_boot_blog_api.domain.PostStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private UUID id;
    private String title;
    private String content;
    private AuthorDto author;
    private CategoryDto category;
    private Set<TagDto> tags;
    private Integer readingTime;
    private LocalDateTime createdAt;
    private LocalDateTime updateddAt;
    private PostStatus status;
}
