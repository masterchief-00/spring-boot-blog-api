package com.kwizera.spring_boot_blog_api.Services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.kwizera.spring_boot_blog_api.domain.entities.Tag;

public interface TagService {
    List<Tag> getTags();

    List<Tag> createTags(Set<String> tagNames);

    Tag getTagById(UUID id);

    void deleteTag(UUID id);

    List<Tag> getTagByIds(Set<UUID> ids);

}
