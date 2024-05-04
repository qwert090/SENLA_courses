package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CategoryDto;
import org.example.service.serviceInterface.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public void createCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteById(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteById(categoryId);
    }

    @PutMapping
    public void updateCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.updateCategory(categoryDto);
    }

    @GetMapping("/{categoryId}")
    public CategoryDto getById(@PathVariable("categoryId") Long categoryId) {
        return categoryService.getById(categoryId);
    }
}
