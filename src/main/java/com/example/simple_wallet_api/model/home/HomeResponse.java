package com.example.simple_wallet_api.model.home;

import com.example.simple_wallet_api.model.transaction.TransactionResponse;
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
