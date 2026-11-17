package com.ezybank.account.controller;

import com.ezybank.account.dto.AccountResponse;
import com.ezybank.account.dto.CreateAccountRequest;
import com.ezybank.account.service.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private static final Logger log =
            LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/hello")
    public String hello() {

        log.info("Received request for GET /api/accounts/hello");

        return "Account service is running";
    }

    @GetMapping("/sample")
    public AccountResponse getSampleAccount() {

        log.info("Received request for GET /api/accounts/sample");

        return new AccountResponse(
                1L,
                "ACC1001",
                "Jerin",
                new java.math.BigDecimal("1500.00")
        );
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @RequestBody CreateAccountRequest request) {

        log.info(
                "Received request to create account for holder {}",
                request.getAccountHolder()
        );

        AccountResponse response =
                accountService.createAccount(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}