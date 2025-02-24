package com.kwizera.spring_boot_blog_api.domain.Dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagDto {

    private UUID id;
    private String name;
    private Integer postCount;
}
