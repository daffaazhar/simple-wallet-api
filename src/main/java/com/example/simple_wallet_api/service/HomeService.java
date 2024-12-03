package com.example.simple_wallet_api.service;

import com.example.simple_wallet_api.entity.Account;
import com.example.simple_wallet_api.entity.Transaction;
import com.example.simple_wallet_api.entity.User;
import com.example.simple_wallet_api.model.HomeResponse;
import com.example.simple_wallet_api.model.TransactionResponse;
import com.example.simple_wallet_api.repository.AccountRepository;
import com.example.simple_wallet_api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class HomeService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    public HomeResponse get(User user) {
        double totalBalance = accountRepository.findAll().stream().mapToDouble(Account::getBalance).sum();

        double totalIncomeThisMonth = transactionRepository.findByUserAndTypeAndDateBetween(user, Transaction.Type.INCOME,
                        LocalDate.now().withDayOfMonth(1), LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1))
                .stream().mapToDouble(Transaction::getAmount).sum();

        double totalExpenseThisMonth = transactionRepository.findByUserAndTypeAndDateBetween(user, Transaction.Type.EXPENSE,
                        LocalDate.now().withDayOfMonth(1), LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1))
                .stream().mapToDouble(Transaction::getAmount).sum();

        List<Transaction> lastTransactions = transactionRepository.findTop5ByUserOrderByDateDesc(user);

        return HomeResponse.builder()
                .totalBalance(totalBalance)
                .totalIncomeThisMonth(totalIncomeThisMonth)
                .totalExpenseThisMonth(totalExpenseThisMonth)
                .lastTransactions(lastTransactions.stream().map(this::toTransactionResponse).toList())
                .build();
    }

    private TransactionResponse toTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccount().getId())
                .categoryId(transaction.getCategory().getId())
                .type(transaction.getType())
                .date(transaction.getDate())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .build();
    }
}
