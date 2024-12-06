package com.example.simple_wallet_api.controller;

import com.example.simple_wallet_api.entity.User;
import com.example.simple_wallet_api.model.home.HomeResponse;
import com.example.simple_wallet_api.model.WebResponse;
import com.example.simple_wallet_api.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping(
            path = "/api/home",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<HomeResponse> get(User user) {
        HomeResponse response = homeService.get(user);
        return WebResponse.<HomeResponse>builder().data(response).build();
    }
}
