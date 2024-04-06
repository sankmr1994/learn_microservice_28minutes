package com.learn.microservices.currencyexchangeservice.dto.errorDto;

import java.time.LocalDateTime;

public class ErrorDto {

    private String errorMsg;

    private LocalDateTime timeStamp;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
