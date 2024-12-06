package com.example.simple_wallet_api.service;

import com.example.simple_wallet_api.entity.User;
import com.example.simple_wallet_api.model.analytic.AnalyticResponse;
import com.example.simple_wallet_api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnalyticService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    public List<AnalyticResponse> getIncomeAnalytic(User user, int month, int year) {
        List<Object[]> results = transactionRepository.getIncomeAnalytics(user, month, year);

        double totalIncome = results.stream()
                .mapToDouble(result -> (double) result[1])
                .sum();

        return results.stream()
                .map(result -> new AnalyticResponse(
                        (String) result[0],
                        (double) result[1],
                        (double) result[1] / totalIncome * 100
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<AnalyticResponse> getExpenseAnalytic(User user, int month, int year) {
        List<Object[]> results = transactionRepository.getExpenseAnalytics(user, month, year);

        double totalExpense = results.stream()
                .mapToDouble(result -> (double) result[1])
                .sum();

        return results.stream()
                .map(result -> new AnalyticResponse(
                        (String) result[0],
                        (double) result[1],
                        (double) result[1] / totalExpense * 100
                ))
                .toList();
    }
}
