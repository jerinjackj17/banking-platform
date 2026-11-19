package com.ezybank.transaction.event;
import java.math.BigDecimal;
import java.time.Instant;
public class TransactionCompletedEvent {
    private final String transactionId;
    private final Long fromAccountId;
    private final Long toAccountId;
    private final BigDecimal amount;
    private final String status;
    private final Instant occurredAt;
    public TransactionCompletedEvent(String transactionId,Long fromAccountId,Long toAccountId,BigDecimal amount,String status,Instant occurredAt){
        this.transactionId=transactionId;this.fromAccountId=fromAccountId;this.toAccountId=toAccountId;this.amount=amount;this.status=status;this.occurredAt=occurredAt;
    }
    public String getTransactionId(){return transactionId;}
    public Long getFromAccountId(){return fromAccountId;}
    public Long getToAccountId(){return toAccountId;}
    public BigDecimal getAmount(){return amount;}
    public String getStatus(){return status;}
    public Instant getOccurredAt(){return occurredAt;}
}
