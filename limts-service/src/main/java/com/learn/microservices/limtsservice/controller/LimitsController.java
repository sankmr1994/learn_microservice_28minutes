package com.learn.microservices.limtsservice.controller;

import com.learn.microservices.limtsservice.bean.Limits;
import com.learn.microservices.limtsservice.configuration.LimitsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private LimitsConfig limitsConfig;

    @GetMapping("/limits")
    public Limits getLimits(){
        return new Limits(1,10);
    }

    @GetMapping("/config/limits")
    public Limits getLimitsFromApplicationProperties(){
        return new Limits(limitsConfig.getMinimum(), limitsConfig.getMaximum());
    }
}
