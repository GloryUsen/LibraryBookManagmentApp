package com.glory.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glory.dto.CategoryRequestDto;
import com.glory.dto.CategoryResponseDto;
import com.glory.dto.PageCategoryResponse;
import com.glory.service.CategoryService;
import com.glory.utils.AppConstants;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {



    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto categoryDto){
        CategoryResponseDto savedCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getAllSingleCategory (@PathVariable("id") Long categoryId){
        CategoryResponseDto categoryDto =  categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(categoryDto);
        
    }

   @GetMapping
    public ResponseEntity <PageCategoryResponse> getAllCategories(
        @RequestParam(value = "PageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
        @RequestParam(value = "PageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_PAGE_SORT_BY) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_PAGE_DIRECTION) String direction
 ){
        
        return ResponseEntity.ok(categoryService.getAllCategories(pageNo, pageSize, sortBy, direction));
    }


    @PutMapping("{id}")
     public ResponseEntity<CategoryResponseDto> updateCategory(@RequestBody CategoryRequestDto categoryDto, @PathVariable("id")  Long categoryId){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
        
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully!");


    }

}
