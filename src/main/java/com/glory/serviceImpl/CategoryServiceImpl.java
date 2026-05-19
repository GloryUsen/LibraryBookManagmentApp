package com.glory.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.glory.dto.CategoryDto;
import com.glory.entity.Category;
import com.glory.exception.ResourceNotFoundException;
import com.glory.repository.CategoryRepository;
import com.glory.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepository  categoryRepository, ModelMapper mapper){
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        // Category category = new Category();
        // category.setName(categoryDto.getName());
        // category.setDescription(categoryDto.getDescription());


        Category category = mapCategoryDtoToCategoryEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return mapCategoryEntityToCategoryDto(savedCategory);

    }


    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        return mapCategoryEntityToCategoryDto(category);
        
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categories =  categoryRepository.findAll();


        return categories.stream()
        .map(this::mapCategoryEntityToCategoryDto)
        .collect(Collectors.toList());

        
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {

       Category category =  categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));


        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category updateCategory = categoryRepository.save(category);

        return mapCategoryEntityToCategoryDto(updateCategory);

        
    }

    @Override
    public void deleteCategory(Long id) {

     Category category =  categoryRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
         categoryRepository.delete(category);
    }



    private CategoryDto mapCategoryEntityToCategoryDto(Category category){

        CategoryDto dto = mapper.map(category, CategoryDto.class);

        // CategoryDto dto = new CategoryDto();

        // dto.setId(category.getId());
        // dto.setName(category.getName());
        // dto.setDescription(category.getDescription());

        return dto;

    }

    private Category mapCategoryDtoToCategoryEntity(CategoryDto dto){

        Category category = mapper.map(dto, Category.class);

        // Category category = new Category();

        // category.setName(dto.getName());
        // category.setDescription(dto.getDescription());

        return category;

    }
    

}
