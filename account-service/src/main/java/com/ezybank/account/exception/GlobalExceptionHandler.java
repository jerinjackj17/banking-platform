package com.ezybank.account.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Map<String,Object>> notFound(AccountNotFoundException ex){
        return response(HttpStatus.NOT_FOUND,"ACCOUNT_NOT_FOUND",ex.getMessage());
    }

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<Map<String,Object>> invalidAmount(InvalidAmountException ex){
        return response(HttpStatus.BAD_REQUEST,"INVALID_AMOUNT",ex.getMessage());
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<Map<String,Object>> insufficient(InsufficientFundsException ex){
        return response(HttpStatus.CONFLICT,"INSUFFICIENT_FUNDS",ex.getMessage());
    }

    private ResponseEntity<Map<String,Object>> response(HttpStatus status,String error,String message){
        Map<String,Object> body=new LinkedHashMap<>();
        body.put("timestamp",Instant.now());
        body.put("status",status.value());
        body.put("error",error);
        body.put("message",message);
        return ResponseEntity.status(status).body(body);
    }
}
