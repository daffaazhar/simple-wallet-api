package com.example.simple_wallet_api.model;

import com.example.simple_wallet_api.entity.Transaction.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTransactionRequest {
    @NotBlank
    private String accountId;

    @NotBlank
    private String categoryId;

    @NotNull
    private LocalDate date;

    private Type type;

    @NotNull
    private double amount;

    @NotBlank
    private String description;
}
