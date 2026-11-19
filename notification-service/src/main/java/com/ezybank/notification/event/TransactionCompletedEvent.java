package com.ezybank.notification.event;
import java.math.BigDecimal;
import java.time.Instant;
public class TransactionCompletedEvent {
    private String transactionId;
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
    private String status;
    private Instant occurredAt;
    public TransactionCompletedEvent(){}
    public String getTransactionId(){return transactionId;}
    public void setTransactionId(String transactionId){this.transactionId=transactionId;}
    public Long getFromAccountId(){return fromAccountId;}
    public void setFromAccountId(Long fromAccountId){this.fromAccountId=fromAccountId;}
    public Long getToAccountId(){return toAccountId;}
    public void setToAccountId(Long toAccountId){this.toAccountId=toAccountId;}
    public BigDecimal getAmount(){return amount;}
    public void setAmount(BigDecimal amount){this.amount=amount;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
    public Instant getOccurredAt(){return occurredAt;}
    public void setOccurredAt(Instant occurredAt){this.occurredAt=occurredAt;}
}
