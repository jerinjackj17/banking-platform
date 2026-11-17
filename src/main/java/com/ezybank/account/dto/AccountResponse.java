package com.ezybank.account.dto;

import java.math.BigDecimal;

public class AccountResponse {

    private long id;
    private String accountNumber;
    private String accountHolder;
    private BigDecimal balance;

    public AccountResponse(long id, String accountNumber, String accountHolder, BigDecimal balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    
    
}
