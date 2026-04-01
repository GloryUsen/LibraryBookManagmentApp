package com.glory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glory.dto.CategoryDto;
import com.glory.service.CategoryService;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {



    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);

    }

}
