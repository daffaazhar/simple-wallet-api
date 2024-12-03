package com.example.simple_wallet_api.controller;

import com.example.simple_wallet_api.entity.User;
import com.example.simple_wallet_api.model.AnalyticResponse;
import com.example.simple_wallet_api.model.WebResponse;
import com.example.simple_wallet_api.service.AnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class AnalyticController {
    @Autowired
    private AnalyticService analyticService;

    @GetMapping(
            path = "/api/analytic/income",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AnalyticResponse>> getIncomeAnalytic(
            User user,
            @RequestParam int month,
            @RequestParam int year
    ) {
        List<AnalyticResponse> response = analyticService.getIncomeAnalytic(user, month, year);
        return WebResponse.<List<AnalyticResponse>>builder().data(response).build();
    }

    @GetMapping(
            path = "/api/analytic/expense",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AnalyticResponse>> getExpenseAnalytic(
            User user,
            @RequestParam int month,
            @RequestParam int year
    ) {
        List<AnalyticResponse> response = analyticService.getExpenseAnalytic(user, month, year);
        return WebResponse.<List<AnalyticResponse>>builder().data(response).build();
    }
}
