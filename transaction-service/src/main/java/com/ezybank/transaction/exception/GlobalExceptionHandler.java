package com.ezybank.transaction.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>> invalid(IllegalArgumentException ex){
        return response(HttpStatus.BAD_REQUEST,"INVALID_TRANSFER",ex.getMessage());
    }

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<Map<String,Object>> accountService(RestClientResponseException ex){
        return response(HttpStatus.BAD_GATEWAY,"ACCOUNT_SERVICE_ERROR",ex.getResponseBodyAsString());
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
