package com.learn.microservices.currencyexchangeservice.exception;

public class CurrencyExchangeNotException extends RuntimeException {

    public CurrencyExchangeNotException(String error) {
        super(error);
    }
}
