package com.kwizera.spring_boot_blog_api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kwizera.spring_boot_blog_api.domain.CreatePostRequest;
import com.kwizera.spring_boot_blog_api.domain.Dtos.CreatePostRequestDto;
import com.kwizera.spring_boot_blog_api.domain.Dtos.PostDto;
import com.kwizera.spring_boot_blog_api.domain.entities.Post;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto tDto(Post post);

    CreatePostRequest tCreatePostRequest(CreatePostRequestDto dto);

}
