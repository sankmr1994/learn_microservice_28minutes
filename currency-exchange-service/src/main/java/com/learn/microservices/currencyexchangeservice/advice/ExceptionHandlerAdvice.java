package com.learn.microservices.currencyexchangeservice.advice;

import com.learn.microservices.currencyexchangeservice.dto.errorDto.ErrorDto;
import com.learn.microservices.currencyexchangeservice.exception.CurrencyExchangeNotException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(CurrencyExchangeNotException.class)
    public ResponseEntity<ErrorDto> handleCurrencyExchangeNotFoundException(CurrencyExchangeNotException exception) {
        ErrorDto error = new ErrorDto();
        error.setErrorMsg(exception.getMessage());
        error.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
