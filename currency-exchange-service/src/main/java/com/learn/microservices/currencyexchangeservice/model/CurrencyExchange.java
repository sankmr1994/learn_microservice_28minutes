package com.learn.microservices.currencyexchangeservice.model;

import com.learn.microservices.currencyexchangeservice.dto.responseDto.CurrencyExchangeResponseDto;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class CurrencyExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_from")
    private String from;

    @Column(name = "currency_to")
    private String to;
    private BigDecimal conversionMultiple;

    public CurrencyExchange() {
    }

    public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    public CurrencyExchangeResponseDto map() {
        CurrencyExchangeResponseDto exchangeRequestDto = new CurrencyExchangeResponseDto();
        exchangeRequestDto.setId(this.getId());
        exchangeRequestDto.setFrom(this.getFrom());
        exchangeRequestDto.setTo(this.getTo());
        exchangeRequestDto.setConversionMultiple(this.getConversionMultiple());
        return exchangeRequestDto;
    }
}
