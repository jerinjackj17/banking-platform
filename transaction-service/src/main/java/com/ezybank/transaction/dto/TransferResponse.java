package com.ezybank.transaction.dto;
import java.math.BigDecimal;
public class TransferResponse {
    private final String transactionId;
    private final Long fromAccountId;
    private final Long toAccountId;
    private final BigDecimal amount;
    private final String status;
    public TransferResponse(String transactionId,Long fromAccountId,Long toAccountId,BigDecimal amount,String status){
        this.transactionId=transactionId;this.fromAccountId=fromAccountId;this.toAccountId=toAccountId;this.amount=amount;this.status=status;
    }
    public String getTransactionId(){return transactionId;}
    public Long getFromAccountId(){return fromAccountId;}
    public Long getToAccountId(){return toAccountId;}
    public BigDecimal getAmount(){return amount;}
    public String getStatus(){return status;}
}
