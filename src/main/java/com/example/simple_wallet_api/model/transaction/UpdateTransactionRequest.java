package com.example.simple_wallet_api.model.transaction;

import com.example.simple_wallet_api.entity.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UpdateTransactionRequest {
    @JsonIgnore
    @NotNull
    private String id;
    
    @NotBlank
    private String accountId;

    @NotBlank
    private String categoryId;

    @NotNull
    private LocalDate date;

    private Transaction.Type type;

    @NotNull
    private double amount;

    @NotBlank
    private String description;
}
