package com.ezybank.notification.controller;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @GetMapping("/hello")
    public String hello(){return "Notification service is running";}
}
