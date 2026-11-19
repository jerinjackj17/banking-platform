package com.ezybank.account.exception;
import java.math.BigDecimal;
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(Long accountId,BigDecimal balance,BigDecimal requested){
        super("Insufficient funds for account "+accountId+". Balance="+balance+", requested="+requested);
    }
}
