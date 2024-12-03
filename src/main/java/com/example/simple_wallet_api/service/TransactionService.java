package com.example.simple_wallet_api.service;

import com.example.simple_wallet_api.entity.Account;
import com.example.simple_wallet_api.entity.Category;
import com.example.simple_wallet_api.entity.Transaction;
import com.example.simple_wallet_api.entity.User;
import com.example.simple_wallet_api.model.CreateTransactionRequest;
import com.example.simple_wallet_api.model.TransactionResponse;
import com.example.simple_wallet_api.repository.AccountRepository;
import com.example.simple_wallet_api.repository.CategoryRepository;
import com.example.simple_wallet_api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TransactionResponse create(User user, CreateTransactionRequest request) {
        validationService.validate(request);

        Category category = categoryRepository.findFirstByUserAndId(user, request.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        Account account = accountRepository.findFirstByUserAndId(user, request.getAccountId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID().toString());
        transaction.setUser(user);
        transaction.setAccount(account);
        transaction.setCategory(category);
        transaction.setType(request.getType());
        transaction.setDate(request.getDate());
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());

        if (request.getType() == Transaction.Type.EXPENSE) {
            account.setBalance(account.getBalance() - request.getAmount());
        } else {
            account.setBalance(account.getBalance() + request.getAmount());
        }

        transactionRepository.save(transaction);
        accountRepository.save(account);

        return toTransactionResponse(transaction);
    }

    @Transactional(readOnly = true)
    public List<TransactionResponse> get(
            User user,
            @RequestParam int month,
            @RequestParam int year
    ) {
        List<Transaction> transactions = transactionRepository.findByUserAndMonthAndYear(user, month, year);
        return transactions.stream().map(this::toTransactionResponse).toList();
    }

    @Transactional
    public void delete(User user, String transactionId) {
        Transaction transaction = transactionRepository.findFirstByUserAndId(user, transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));

        Account account = accountRepository.findFirstByUserAndId(user, transaction.getAccount().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        account.setBalance(account.getBalance() + transaction.getAmount());

        accountRepository.save(account);
        transactionRepository.delete(transaction);
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
