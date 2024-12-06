package com.example.simple_wallet_api.model.category;

import com.example.simple_wallet_api.entity.Category.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private String id;
    private String name;
    private Type type;
}
