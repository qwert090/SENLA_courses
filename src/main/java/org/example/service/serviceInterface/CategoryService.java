package org.example.service.serviceInterface;

import org.example.dto.CategoryDto;

public interface CategoryService {

    void createCategory(CategoryDto categoryDto);

    void deleteById(Long id);

    CategoryDto getById(Long id);

    void updateCategory(CategoryDto categoryDto);
}
