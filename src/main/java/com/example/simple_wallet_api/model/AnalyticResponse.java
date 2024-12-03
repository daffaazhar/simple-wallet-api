package com.example.simple_wallet_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnalyticResponse {
    private String category;
    private double totalAmount;
    private double percentage;
}
