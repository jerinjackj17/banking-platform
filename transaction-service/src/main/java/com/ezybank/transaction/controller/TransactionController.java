package com.ezybank.transaction.controller;

import com.ezybank.transaction.dto.TransferRequest;
import com.ezybank.transaction.dto.TransferResponse;
import com.ezybank.transaction.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService=transactionService;
    }

    @GetMapping("/hello")
    public String hello(){return "Transaction service is running";}

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.transfer(request));
    }
}
