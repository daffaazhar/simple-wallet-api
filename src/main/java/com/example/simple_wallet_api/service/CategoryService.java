package com.example.simple_wallet_api.service;

import com.example.simple_wallet_api.entity.Category;
import com.example.simple_wallet_api.entity.User;
import com.example.simple_wallet_api.model.CategoryResponse;
import com.example.simple_wallet_api.model.CreateCategoryRequest;
import com.example.simple_wallet_api.model.UpdateCategoryRequest;
import com.example.simple_wallet_api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public CategoryResponse create(User user, CreateCategoryRequest request) {
        validationService.validate(request);

        Category category = new Category();
        category.setId(UUID.randomUUID().toString());
        category.setName(request.getName());
        category.setType(request.getType());
        category.setUser(user);

        categoryRepository.save(category);

        return toCategoryResponse(category);
    }

    public CategoryResponse update(User user, UpdateCategoryRequest request) {
        validationService.validate(request);

        Category category = categoryRepository.findFirstByUserAndId(user, request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        category.setName(request.getName());
        categoryRepository.save(category);

        return toCategoryResponse(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> getAll(User user) {
        List<Category> categories = categoryRepository.findAllByUser(user);
        return categories.stream().map(this::toCategoryResponse).toList();
    }

    @Transactional
    public void delete(User user, String categoryId) {
        Category category = categoryRepository.findFirstByUserAndId(user, categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        categoryRepository.delete(category);
    }

    private CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType())
                .build();
    }
}
