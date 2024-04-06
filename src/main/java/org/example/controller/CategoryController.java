package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CategoryDto;
import org.example.service.serviceInterface.CategoryService;
import org.example.utils.JsonMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final JsonMapper json;

    public void createCategory(String serializedCategory){
        CategoryDto categoryDto = json.deserialize(serializedCategory, CategoryDto.class);
        categoryService.createCategory(categoryDto);
    }

    public void deleteById(long categoryId){
        categoryService.deleteById(categoryId);
    }

    public void updateCategory(String serializedCategory){
        CategoryDto categoryDto = json.deserialize(serializedCategory, CategoryDto.class);
        categoryService.updateCategory(categoryDto);
    }

    public CategoryDto getById(long categoryId){
        return categoryService.getById(categoryId);
    }
}
