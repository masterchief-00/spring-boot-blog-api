package com.kwizera.spring_boot_blog_api.Services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kwizera.spring_boot_blog_api.Services.CategoryServices;
import com.kwizera.spring_boot_blog_api.domain.entities.Category;
import com.kwizera.spring_boot_blog_api.repositories.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryServices {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {

        String categoryName = category.getName();

        if (categoryRepository.existsByNameIgnoreCase(categoryName))
            throw new IllegalArgumentException("Category already exists with name: " + categoryName);

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            if (category.get().getPosts().size() > 0)
                throw new IllegalStateException("Category has posts associated with it");
        }

        categoryRepository.deleteById(id);

    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
    }

}
