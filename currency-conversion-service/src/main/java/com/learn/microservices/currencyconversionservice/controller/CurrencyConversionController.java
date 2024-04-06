package com.learn.microservices.currencyconversionservice.controller;

import com.learn.microservices.currencyconversionservice.dto.response.CurrencyConversionResponseDto;
import com.learn.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    private Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    private RestTemplate restTemplate;

    private CurrencyExchangeProxy currencyExchangeProxy;

    public static final String BASE_URL = "http://localhost:8000/currency-exchange/from/{currencyFrom}/to/{currencyTo}";

    public CurrencyConversionController(RestTemplate restTemplate, CurrencyExchangeProxy currencyExchangeProxy) {
        this.restTemplate = restTemplate;
        this.currencyExchangeProxy = currencyExchangeProxy;
    }

    @GetMapping("/currency-conversion/from/{currencyFrom}/to/{currencyTo}/quantity/{qty}")
    public CurrencyConversionResponseDto retrieveCurrencyConversion(@PathVariable String currencyFrom,
                                                                    @PathVariable String currencyTo,
                                                                    @PathVariable Integer qty) {

        //CHANGE-KUBERNETES
        logger.info("calculateCurrencyConversion called with {} to {} with {}", currencyFrom, currencyTo, qty);

        HashMap<String, String> uriMap = new HashMap<>();
        uriMap.put("currencyFrom", currencyFrom);
        uriMap.put("currencyTo", currencyTo);

        ResponseEntity<CurrencyConversionResponseDto> responseDtoResponseEntity = restTemplate.getForEntity(BASE_URL, CurrencyConversionResponseDto.class, uriMap);
        CurrencyConversionResponseDto responseDto = responseDtoResponseEntity.getBody();

        responseDto.setQuantity(qty);
        responseDto.setTotalCalculatedAmount(new BigDecimal(qty).multiply(responseDto.getConversionMultiple()));
        return responseDto;
    }

    @GetMapping("/currency-conversion-feign/from/{currencyFrom}/to/{currencyTo}/quantity/{qty}")
    public CurrencyConversionResponseDto retrieveCurrencyConversionFeign(@PathVariable String currencyFrom,
                                                                         @PathVariable String currencyTo,
                                                                         @PathVariable Integer qty) {
        //CHANGE-KUBERNETES
        logger.info("retrieveCurrencyConversionFeign called with {} to {} with {}", currencyFrom, currencyTo, qty);

        CurrencyConversionResponseDto responseDto = currencyExchangeProxy.retrieveExchangeValue(currencyFrom, currencyTo);
        responseDto.setQuantity(qty);
        responseDto.setTotalCalculatedAmount(new BigDecimal(qty).multiply(responseDto.getConversionMultiple()));
        return responseDto;
    }
}
