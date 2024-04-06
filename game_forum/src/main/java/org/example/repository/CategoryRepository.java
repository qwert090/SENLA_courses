package org.example.repository;

import lombok.Getter;
import org.example.entity.Category;
import org.example.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
public class CategoryRepository {
    private List<Category> categories = new ArrayList<>();

    public void create(Category category){
        categories.add(category);
    }

    public void delete(long categoryId){
        categories = categories.stream()
                .filter(category -> category.getId() != categoryId)
                .toList();
    }

    public Optional<Category> read(long categoryId){
        Optional<Category> readCategory = categories.stream()
                .filter(id -> id.getId() == categoryId)
                .findFirst();
        return readCategory;
    }

    public void update(Category updateCategory){
        categories = categories.stream()
                .filter(category -> category.getId() == updateCategory.getId())
                .map(category -> updateCategory)
                .toList();
    }
}
