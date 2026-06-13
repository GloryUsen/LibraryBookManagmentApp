package com.glory.service;

import java.util.List;

import com.glory.dto.CategoryRequestDto;
import com.glory.dto.CategoryResponseDto;
import com.glory.dto.PageCategoryResponse;

public interface CategoryService {

    CategoryResponseDto createCategory(CategoryRequestDto categoryDto);

    CategoryResponseDto getCategoryById(Long id);

    PageCategoryResponse getAllCategories(int pageNo,int pageSize,String sortBy,String direction);

    CategoryResponseDto updateCategory(CategoryRequestDto categoryDto, Long categoryId);

    void deleteCategory (Long categoryId);

}
