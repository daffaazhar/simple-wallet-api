package com.example.simple_wallet_api.model;

import com.example.simple_wallet_api.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeResponse {
    private double totalBalance;
    private double totalIncomeThisMonth;
    private double totalExpenseThisMonth;
    private List<TransactionResponse> lastTransactions;
}
