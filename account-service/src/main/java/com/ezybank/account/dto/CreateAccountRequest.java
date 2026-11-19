package com.ezybank.account.dto;
import java.math.BigDecimal;
public class CreateAccountRequest {
    private String accountHolder;
    private BigDecimal initialBalance;
    public String getAccountHolder(){return accountHolder;}
    public void setAccountHolder(String accountHolder){this.accountHolder=accountHolder;}
    public BigDecimal getInitialBalance(){return initialBalance;}
    public void setInitialBalance(BigDecimal initialBalance){this.initialBalance=initialBalance;}
}
