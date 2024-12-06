package com.example.simple_wallet_api.model.category;

import com.example.simple_wallet_api.entity.Category.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCategoryRequest {
    @NotBlank
    @Size(max = 100)
    private String name;

    private Type type;
}
