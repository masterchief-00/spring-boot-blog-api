package com.kwizera.spring_boot_blog_api.Services;

import java.util.List;
import java.util.UUID;

import com.kwizera.spring_boot_blog_api.domain.entities.Category;

public interface CategoryServices {

    List<Category> listCategories();

    Category createCategory(Category category);

    Category getCategoryById(UUID id);

    void deleteCategory(UUID id);
}
