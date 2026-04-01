package com.glory.serviceImpl;

import org.springframework.stereotype.Service;

import com.glory.dto.CategoryDto;
import com.glory.entity.Category;
import com.glory.repository.CategoryRepository;
import com.glory.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository  categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        Category category = new Category();
        category.setName(categoryDto.getName());

        Category savedCategory = categoryRepository.save(category);

        



        return mapCategoryEntityToCategoryDto(savedCategory);
        
    }

    private CategoryDto mapCategoryEntityToCategoryDto(Category category) {

        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        

        return dto;


    }

    

}
