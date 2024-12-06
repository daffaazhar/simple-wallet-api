package com.example.simple_wallet_api.controller;

import com.example.simple_wallet_api.entity.User;
import com.example.simple_wallet_api.model.category.CategoryResponse;
import com.example.simple_wallet_api.model.category.CreateCategoryRequest;
import com.example.simple_wallet_api.model.category.UpdateCategoryRequest;
import com.example.simple_wallet_api.model.WebResponse;
import com.example.simple_wallet_api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping(
            path = "/api/category",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CategoryResponse> create(User user, @RequestBody CreateCategoryRequest request) {
        CategoryResponse response = categoryService.create(user, request);
        return WebResponse.<CategoryResponse>builder().data(response).build();
    }

    @PutMapping(
            path = "/api/category/{categoryId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CategoryResponse> update(
            User user,
            @RequestBody UpdateCategoryRequest request,
            @PathVariable("categoryId") String categoryId
    ) {
        request.setId(categoryId);

        CategoryResponse response = categoryService.update(user, request);
        return WebResponse.<CategoryResponse>builder().data(response).build();
    }

    @DeleteMapping(
            path = "/api/category/{categoryId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user, @PathVariable("categoryId") String categoryId) {
        categoryService.delete(user, categoryId);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/category",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<CategoryResponse>> get(User user) {
        List<CategoryResponse> response = categoryService.getAll(user);
        return WebResponse.<List<CategoryResponse>>builder().data(response).build();
    }
}
