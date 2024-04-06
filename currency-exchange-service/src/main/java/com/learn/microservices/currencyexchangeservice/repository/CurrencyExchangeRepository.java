package com.learn.microservices.currencyexchangeservice.repository;

import com.learn.microservices.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    CurrencyExchange findByFromAndTo(String currencyFrom, String currencyTo);
}
