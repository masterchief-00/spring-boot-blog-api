package com.kwizera.spring_boot_blog_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kwizera.spring_boot_blog_api.Services.CategoryServices;
import com.kwizera.spring_boot_blog_api.domain.Dtos.CategoryDto;
import com.kwizera.spring_boot_blog_api.domain.Dtos.CreateCategoryRequest;
import com.kwizera.spring_boot_blog_api.domain.entities.Category;
import com.kwizera.spring_boot_blog_api.mappers.CategoryMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServices categoryServices;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categories = categoryServices
                .listCategories()
                .stream()
                .map(categoryMapper::tDto)
                .toList();

        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CreateCategoryRequest req) {
        Category categoryToCreate = categoryMapper.toEntity(req);

        Category savedCategory = categoryServices.createCategory(categoryToCreate);

        return new ResponseEntity<>(categoryMapper.tDto(savedCategory), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") UUID id) {

        categoryServices.deleteCategory(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
