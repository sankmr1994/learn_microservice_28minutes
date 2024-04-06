package com.learn.microservices.currencyexchangeservice.service;

import com.learn.microservices.currencyexchangeservice.dto.responseDto.CurrencyExchangeResponseDto;
import com.learn.microservices.currencyexchangeservice.exception.CurrencyExchangeNotException;
import com.learn.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.learn.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    private CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchangeService(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    public CurrencyExchangeResponseDto retrieveExchangeValue(String currencyFrom, String currencyTo) {
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(currencyFrom, currencyTo);
        if(currencyExchange == null)
            throw new CurrencyExchangeNotException("Currency exchange not found.");
        return currencyExchange.map();
    }
}
