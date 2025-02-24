package com.kwizera.spring_boot_blog_api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.kwizera.spring_boot_blog_api.domain.PostStatus;
import com.kwizera.spring_boot_blog_api.domain.Dtos.CategoryDto;
import com.kwizera.spring_boot_blog_api.domain.Dtos.CreateCategoryRequest;
import com.kwizera.spring_boot_blog_api.domain.entities.Category;
import com.kwizera.spring_boot_blog_api.domain.entities.Post;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto tDto(Category Category);

    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        if (null == posts)
            return 0;

        return posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();
    }

}
