package com.example.myspringretry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class CheckStatusService {

    @Retryable(
            value = RuntimeException.class,
            maxAttempts = 3,
            backoff = @Backoff(3000)
    )
    public String checkStatus(String trackingNumber){
        System.out.println("Calling another service to get status!!!");
        throw  new RuntimeException("Service not available");
    }

    public String recover(){
        return "Please try after some time";
    }
}
