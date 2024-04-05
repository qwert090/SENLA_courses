package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CategoryDto;
import org.example.entity.Category;
import org.example.exception.EntityNotFoundException;
import org.example.repository.CategoryRepository;
import org.example.service.serviceInterface.CategoryService;
import org.example.utils.Mapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final Mapper mapper;

    @Override
    public void createCategory(CategoryDto categoryDto) {
        Category category = mapper.toEntity(Category.class, categoryDto);
        categoryRepository.create(category);

    }

    @Override
    public void deleteById(long id) {
        categoryRepository.delete(id);

    }

    @Override
    public CategoryDto getById(long id) {
        Category category = categoryRepository.read(id)
                .orElseThrow(() -> new EntityNotFoundException("no such category"));
        System.out.println(categoryRepository.getCategories().stream()
                .filter(category1 -> category1.getId() == id)
                .toList()
        );
        return mapper.toDto(CategoryDto.class, category);
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.read(categoryDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("no such category"));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
    }
}
