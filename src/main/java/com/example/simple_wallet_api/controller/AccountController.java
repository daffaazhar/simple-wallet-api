package com.example.simple_wallet_api.controller;

import com.example.simple_wallet_api.entity.User;
import com.example.simple_wallet_api.model.*;
import com.example.simple_wallet_api.model.account.AccountListResponse;
import com.example.simple_wallet_api.model.account.AccountResponse;
import com.example.simple_wallet_api.model.account.CreateAccountRequest;
import com.example.simple_wallet_api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping(
            path = "/api/account",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AccountResponse> create(User user, @RequestBody CreateAccountRequest request) {
        AccountResponse response = accountService.create(user, request);
        return WebResponse.<AccountResponse>builder().data(response).build();
    }

    @DeleteMapping(
            path = "/api/account/{accountId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user, @PathVariable("accountId") String categoryId) {
        accountService.delete(user, categoryId);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/account",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AccountListResponse> list(User user) {
        AccountListResponse response = accountService.getAll(user);
        return WebResponse.<AccountListResponse>builder().data(response).build();
    }

    @GetMapping(
            path = "/api/account/{accountId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AccountResponse> get(User user, @PathVariable("accountId") String accountId) {
        AccountResponse response = accountService.get(user, accountId);
        return WebResponse.<AccountResponse>builder().data(response).build();
    }
}
