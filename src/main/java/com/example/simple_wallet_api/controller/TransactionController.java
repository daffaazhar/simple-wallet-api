package com.example.simple_wallet_api.controller;

import com.example.simple_wallet_api.entity.User;
import com.example.simple_wallet_api.model.*;
import com.example.simple_wallet_api.model.transaction.CreateTransactionRequest;
import com.example.simple_wallet_api.model.transaction.TransactionResponse;
import com.example.simple_wallet_api.model.transaction.UpdateTransactionRequest;
import com.example.simple_wallet_api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping(
            path = "/api/transaction",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TransactionResponse> create(User user, @RequestBody CreateTransactionRequest request) {
        TransactionResponse response = transactionService.create(user, request);
        return WebResponse.<TransactionResponse>builder().data(response).build();
    }

    @GetMapping(
            path = "/api/transaction",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<TransactionResponse>> get(User user, @RequestParam int month, @RequestParam int year) {
        List<TransactionResponse> response = transactionService.get(user, month, year);
        return WebResponse.<List<TransactionResponse>>builder().data(response).build();
    }

    @DeleteMapping(
            path = "/api/transaction/{transactionId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user, @PathVariable("transactionId") String transactionId) {
        transactionService.delete(user, transactionId);
        return WebResponse.<String>builder().data("OK").build();
    }

    @PutMapping(
            path = "/api/transaction/{transactionId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TransactionResponse> update(User user, @RequestBody UpdateTransactionRequest request, @PathVariable("transactionId") String transactionId) {
        request.setId(transactionId);
        TransactionResponse response = transactionService.update(user, request);
        return WebResponse.<TransactionResponse>builder().data(response).build();
    }
}
