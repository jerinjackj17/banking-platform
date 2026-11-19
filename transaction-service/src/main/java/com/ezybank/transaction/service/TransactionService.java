package com.ezybank.transaction.service;

import com.ezybank.transaction.dto.AmountRequest;
import com.ezybank.transaction.dto.TransferRequest;
import com.ezybank.transaction.dto.TransferResponse;
import com.ezybank.transaction.event.TransactionCompletedEvent;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TransactionService {

    private final AtomicLong transactionIdGenerator=new AtomicLong(1000);
    private final RestClient accountClient;
    private final KafkaTemplate<String,TransactionCompletedEvent> kafkaTemplate;
    private final String transactionTopic;

    public TransactionService(
        RestClient.Builder restClientBuilder,
        KafkaTemplate<String,TransactionCompletedEvent> kafkaTemplate,
        @Value("${account.service.base-url}") String accountBaseUrl,
        @Value("${app.kafka.transaction-topic}") String transactionTopic){

        this.accountClient=restClientBuilder.baseUrl(accountBaseUrl).build();
        this.kafkaTemplate=kafkaTemplate;
        this.transactionTopic=transactionTopic;
    }

    public TransferResponse transfer(TransferRequest request){
        validate(request);

        String transactionId="TXN"+transactionIdGenerator.getAndIncrement();

        withdraw(request.getFromAccountId(),request.getAmount());
        deposit(request.getToAccountId(),request.getAmount());

        TransactionCompletedEvent event=new TransactionCompletedEvent(
            transactionId,
            request.getFromAccountId(),
            request.getToAccountId(),
            request.getAmount(),
            "COMPLETED",
            Instant.now()
        );

        kafkaTemplate.send(transactionTopic,transactionId,event);

        return new TransferResponse(
            transactionId,
            request.getFromAccountId(),
            request.getToAccountId(),
            request.getAmount(),
            "COMPLETED"
        );
    }

    private void withdraw(Long accountId,BigDecimal amount){
        accountClient.post()
            .uri("/api/accounts/{id}/withdraw",accountId)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new AmountRequest(amount))
            .retrieve()
            .toBodilessEntity();
    }

    private void deposit(Long accountId,BigDecimal amount){
        accountClient.post()
            .uri("/api/accounts/{id}/deposit",accountId)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new AmountRequest(amount))
            .retrieve()
            .toBodilessEntity();
    }

    private void validate(TransferRequest request){
        if(request.getFromAccountId()==null || request.getToAccountId()==null || request.getAmount()==null){
            throw new IllegalArgumentException("fromAccountId, toAccountId and amount are required");
        }
        if(request.getFromAccountId().equals(request.getToAccountId())){
            throw new IllegalArgumentException("Source and destination accounts must be different");
        }
        if(request.getAmount().compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("Transfer amount must be greater than zero");
        }
    }
}
