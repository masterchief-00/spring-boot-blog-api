package com.kwizera.spring_boot_blog_api.domain.Dtos;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTagsRequest {

    @NotEmpty(message = "At least one tag name is expected")
    @Size(max = 10, message = "Maximum {max} tags allowed")
    private Set<@Size(min = 2, max = 30, message = "Tag name must be between {min} and {max} characters") @Pattern(regexp = "^[\\w\\s-]+$", message = "tag name can only contain letters, numbers, hyphens and spaces") String> names;
}
