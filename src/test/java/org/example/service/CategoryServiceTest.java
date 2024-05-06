package org.example.service;

import org.example.config.ApplicationConfigTest;
import org.example.dto.CategoryDto;
import org.example.entity.Category;
import org.example.repository.impl.CategoryRepository;
import org.example.utils.CustomMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ApplicationConfigTest.class})
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CustomMapper mapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void createCategoryTest() {
        Category category = new Category();
        CategoryDto categoryDto = new CategoryDto();
        when(mapper.toEntity(Category.class, categoryDto)).thenReturn(category);
        categoryService.createCategory(categoryDto);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void updateCategoryTest() {
        Category category = new Category();
        CategoryDto categoryDto = new CategoryDto();
        when(mapper.toEntity(Category.class, categoryDto)).thenReturn(category);
        categoryService.updateCategory(categoryDto);
        verify(categoryRepository, times(1)).update(category);
    }

    @Test
    public void getCategoryByIdTest() {
        long categoryId = 1L;
        Category category = new Category();
        CategoryDto categoryDto = new CategoryDto();
        when(mapper.toDto(CategoryDto.class, category)).thenReturn(categoryDto);
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        CategoryDto result = categoryService.getById(categoryId);
        assertSame(categoryDto, result);
    }

    @Test
    public void deleteCategoryByIdTest() {
        long categoryId = 1L;
        categoryService.deleteById(categoryId);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}
