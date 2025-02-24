package com.kwizera.spring_boot_blog_api.mappers;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.kwizera.spring_boot_blog_api.domain.PostStatus;
import com.kwizera.spring_boot_blog_api.domain.Dtos.TagDto;
import com.kwizera.spring_boot_blog_api.domain.entities.Post;
import com.kwizera.spring_boot_blog_api.domain.entities.Tag;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    TagDto toTagResponse(Tag tag);

    @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> posts) {
        if (null == posts)
            return 0;

        return (int) posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();
    }
}
