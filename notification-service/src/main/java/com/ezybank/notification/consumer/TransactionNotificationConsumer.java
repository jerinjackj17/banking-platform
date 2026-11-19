package com.ezybank.notification.consumer;

import com.ezybank.notification.event.TransactionCompletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionNotificationConsumer {

    private static final Logger log=LoggerFactory.getLogger(TransactionNotificationConsumer.class);

    @KafkaListener(
        topics="${app.kafka.transaction-topic}",
        groupId="${spring.kafka.consumer.group-id}"
    )
    public void handleTransactionCompleted(TransactionCompletedEvent event){
        log.info(
            "NOTIFICATION: Transaction {} completed. {} transferred from account {} to account {}",
            event.getTransactionId(),
            event.getAmount(),
            event.getFromAccountId(),
            event.getToAccountId()
        );
    }
}
