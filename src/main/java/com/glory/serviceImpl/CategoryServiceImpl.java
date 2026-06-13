package com.glory.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.glory.dto.CategoryRequestDto;
import com.glory.dto.CategoryResponseDto;
import com.glory.dto.PageCategoryResponse;
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
    public CategoryResponseDto createCategory(CategoryRequestDto categoryDto) {

        // Category category = new Category();
        // category.setName(categoryDto.getName());
        // category.setDescription(categoryDto.getDescription());


        Category category = mapCategoryRequestDtoToCategoryEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return mapCategoryEntityToCategoryResponseDto(savedCategory);

    }


    @Override
    public CategoryResponseDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        return mapCategoryEntityToCategoryResponseDto(category);
        
    }

    @Override
    public PageCategoryResponse getAllCategories(
            int pageNo,
            int pageSize,
            String sortBy,
            String direction){

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Category> categories = categoryRepository.findAll(pageable);

        List<CategoryResponseDto> contents = categories.getContent()
                .stream()
                .map(this::mapCategoryEntityToCategoryResponseDto)
                .collect(Collectors.toList());

        PageCategoryResponse response = new PageCategoryResponse();
        response.setContent(contents);
        response.setPageNo(categories.getNumber());
        response.setPageSize(categories.getSize());
        response.setTotalElements(categories.getTotalElements());
        response.setTotalPages(categories.getTotalPages());
        response.setLast(categories.isLast());

        return response;
        }
        

    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto categoryDto, Long categoryId) {

       Category category =  categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));


        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category updateCategory = categoryRepository.save(category);

        return mapCategoryEntityToCategoryResponseDto(updateCategory);

        
    }

    @Override
    public void deleteCategory(Long id) {

     Category category =  categoryRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
         categoryRepository.delete(category);
    }



    private CategoryResponseDto mapCategoryEntityToCategoryResponseDto(Category category){

        return mapper.map(category, CategoryResponseDto.class);

    }

    private Category mapCategoryRequestDtoToCategoryEntity(CategoryRequestDto dto){
        return mapper.map(dto, Category.class);

    }
    

}
