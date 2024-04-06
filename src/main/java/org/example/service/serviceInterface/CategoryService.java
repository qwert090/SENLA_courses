package org.example.service.serviceInterface;

import org.example.dto.CategoryDto;

public interface CategoryService {

    void createCategory(CategoryDto categoryDto);

    void deleteById(long id);

    CategoryDto getById(long id);

    void updateCategory(CategoryDto categoryDto);
}
