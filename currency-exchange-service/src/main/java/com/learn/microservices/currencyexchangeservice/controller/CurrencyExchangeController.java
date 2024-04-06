package com.learn.microservices.currencyexchangeservice.controller;

import com.learn.microservices.currencyexchangeservice.dto.responseDto.CurrencyExchangeResponseDto;
import com.learn.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    private CurrencyExchangeService currencyExchangeService;

    private Environment environment;


    public CurrencyExchangeController(CurrencyExchangeService currencyExchangeService, Environment environment) {
        this.currencyExchangeService = currencyExchangeService;
        this.environment = environment;
    }


    @GetMapping("/currency-exchange/from/{currencyFrom}/to/{currencyTo}")
    public CurrencyExchangeResponseDto retrieveExchangeValue(@PathVariable("currencyFrom") String from, @PathVariable("currencyTo") String to) {

        logger.info("retrieveExchangeValue called with {} to {}", from, to);

        CurrencyExchangeResponseDto currencyExchangeResponseDto = currencyExchangeService.retrieveExchangeValue(from, to);
        String port = environment.getProperty("local.server.port");

        //CHANGE-KUBERNETES
        String host = environment.getProperty("HOSTNAME");
        String version = "v11";

        currencyExchangeResponseDto.setEnvironment(port + " " + version + " " + host);

        return currencyExchangeResponseDto;
    }
}
