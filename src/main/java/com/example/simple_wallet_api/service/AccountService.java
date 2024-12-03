package com.example.simple_wallet_api.service;

import com.example.simple_wallet_api.entity.Account;
import com.example.simple_wallet_api.entity.User;
import com.example.simple_wallet_api.model.AccountListResponse;
import com.example.simple_wallet_api.model.AccountResponse;
import com.example.simple_wallet_api.model.CreateAccountRequest;
import com.example.simple_wallet_api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public AccountResponse create(User user, CreateAccountRequest request) {
        validationService.validate(request);

        Account account = new Account();
        account.setId(UUID.randomUUID().toString());
        account.setName(request.getName());
        account.setBalance(request.getBalance());
        account.setUser(user);

        accountRepository.save(account);

        return toAccountResponse(account);
    }

    @Transactional
    public void delete(User user, String accountId) {
        Account account = accountRepository.findFirstByUserAndId(user, accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        accountRepository.delete(account);
    }

    @Transactional(readOnly = true)
    public AccountListResponse getAll(User user) {
        List<Account> accounts = accountRepository.findAllByUser(user);

        double assets = accounts.stream()
                .filter(account -> account.getBalance() >= 0)
                .mapToDouble(Account::getBalance)
                .sum();

        double debts = accounts.stream()
                .filter(account -> account.getBalance() < 0)
                .mapToDouble(Account::getBalance)
                .sum();

        double total = assets + debts;

        List<AccountResponse> accountResponses = accounts.stream()
                .map(this::toAccountResponse)
                .toList();

        return AccountListResponse.builder()
                .assets(assets)
                .debts(debts)
                .total(total)
                .accounts(accountResponses)
                .build();
    }

    @Transactional(readOnly = true)
    public AccountResponse get(User user, String accountId) {
        Account account = accountRepository.findFirstByUserAndId(user, accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        return toAccountResponse(account);
    }

    private AccountResponse toAccountResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .name(account.getName())
                .balance(account.getBalance())
                .build();
    }
}
