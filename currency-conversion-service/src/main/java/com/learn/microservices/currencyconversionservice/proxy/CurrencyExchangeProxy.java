package com.learn.microservices.currencyconversionservice.proxy;

import com.learn.microservices.currencyconversionservice.dto.response.CurrencyConversionResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
//@FeignClient(name = "currency-exchange-service")
//@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{currencyFrom}/to/{currencyTo}")
    CurrencyConversionResponseDto retrieveExchangeValue(@PathVariable String currencyFrom, @PathVariable String currencyTo);
}
