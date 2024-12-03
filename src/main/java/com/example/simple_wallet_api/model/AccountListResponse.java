package com.example.simple_wallet_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountListResponse {
    private double assets;
    private double debts;
    private double total;
    private List<AccountResponse> accounts;
}
