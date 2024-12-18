package com.example.simple_wallet_api.model.transaction;

import com.example.simple_wallet_api.entity.Transaction.Type;
import com.example.simple_wallet_api.model.account.AccountResponse;
import com.example.simple_wallet_api.model.category.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private String id;
    private Type type;
    private LocalDate date;
    private double amount;
    private String description;
    private AccountResponse account;
    private CategoryResponse category;
}
