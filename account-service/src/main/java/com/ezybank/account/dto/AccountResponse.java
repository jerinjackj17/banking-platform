package com.ezybank.account.dto;
import java.math.BigDecimal;
public class AccountResponse {
    private final long id;
    private final String accountNumber;
    private final String accountHolder;
    private final BigDecimal balance;
    public AccountResponse(long id,String accountNumber,String accountHolder,BigDecimal balance){
        this.id=id; this.accountNumber=accountNumber; this.accountHolder=accountHolder; this.balance=balance;
    }
    public long getId(){return id;}
    public String getAccountNumber(){return accountNumber;}
    public String getAccountHolder(){return accountHolder;}
    public BigDecimal getBalance(){return balance;}
}
