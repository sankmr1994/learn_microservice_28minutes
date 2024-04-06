package com.learn.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    private static int requestCount = 1;

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api",fallbackMethod = "hardcodedResponse")
//    @CircuitBreaker(name = "sample-api", fallbackMethod = "hardcodedResponse")
    @RateLimiter(name = "sample-api", fallbackMethod = "hardcodedResponse")
    public String sampleApi() {
        System.out.println(String.format("Retry Count %d ", requestCount++));
//        String res = new RestTemplate().getForObject("http://localhost:8080/dummy-url", String.class);
        return "res";
    }

    public String hardcodedResponse(Exception exception) {
        return "hardcode data";
    }
}
