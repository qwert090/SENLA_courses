package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CategoryDto;
import org.example.entity.Category;
import org.example.exception.entityNotFound.CategoryNotFoundException;
import org.example.repository.impl.CategoryRepository;
import org.example.service.serviceInterface.CategoryService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CustomMapper mapper;

    @Override
    public void createCategory(CategoryDto categoryDto) {
        Category category = mapper.toEntity(Category.class, categoryDto);
        categoryRepository.save(category);

    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);

    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("id " + id));
        return mapper.toDto(CategoryDto.class, category);
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        Category updateCategory = mapper.toEntity(Category.class, categoryDto);
        categoryRepository.update(updateCategory);
    }
}
