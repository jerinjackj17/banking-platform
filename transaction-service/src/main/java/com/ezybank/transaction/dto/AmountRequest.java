package com.ezybank.transaction.dto;
import java.math.BigDecimal;
public class AmountRequest {
    private final BigDecimal amount;
    public AmountRequest(BigDecimal amount){this.amount=amount;}
    public BigDecimal getAmount(){return amount;}
}
