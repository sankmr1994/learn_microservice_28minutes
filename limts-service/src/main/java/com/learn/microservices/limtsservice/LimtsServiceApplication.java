package com.learn.microservices.limtsservice;

import com.learn.microservices.limtsservice.configuration.LimitsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({LimitsConfig.class})
public class LimtsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimtsServiceApplication.class, args);
	}

}
