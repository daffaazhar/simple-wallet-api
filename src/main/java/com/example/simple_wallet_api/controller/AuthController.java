package com.example.simple_wallet_api.controller;

import com.example.simple_wallet_api.entity.User;
import com.example.simple_wallet_api.model.*;
import com.example.simple_wallet_api.model.auth.LoginResponse;
import com.example.simple_wallet_api.model.auth.LoginUserRequest;
import com.example.simple_wallet_api.model.auth.MeResponse;
import com.example.simple_wallet_api.model.auth.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.simple_wallet_api.service.AuthService;

@CrossOrigin
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(
            path = "/api/auth/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterUserRequest request) {
        authService.register(request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @PostMapping(
            path = "/api/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<LoginResponse> login(@RequestBody LoginUserRequest request) {
        LoginResponse response = authService.login(request);
        return WebResponse.<LoginResponse>builder().data(response).build();
    }

    @GetMapping(
            path = "/api/auth/me",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<MeResponse> get(User user) {
        MeResponse response = authService.me(user);
        return WebResponse.<MeResponse>builder().data(response).build();
    }
}
