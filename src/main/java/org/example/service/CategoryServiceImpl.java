package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CategoryDto;
import org.example.entity.Category;
import org.example.repository.CategoryRepository;
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
    public void deleteById(long id) {
        categoryRepository.deleteById(id);

    }

    @Override
    public CategoryDto getById(long id) {
        Category category = (Category) categoryRepository.findById(id);
        return mapper.toDto(CategoryDto.class, category);
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        categoryRepository.findById(categoryDto.getId());
        Category updateCategory = mapper.toEntity(Category.class, categoryDto);
        categoryRepository.update(updateCategory);
    }
}
