package com.learn.microservices.apigateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
        Function<PredicateSpec, Buildable<Route>> routeFunction = predicateSpec ->
                predicateSpec.path("/get")
                        .filters(gatewayFilterSpec ->
                                gatewayFilterSpec.addRequestHeader("Header", "myHeader"))
                        .uri("http://httpbin.org:80");
        Function<PredicateSpec, Buildable<Route>> currencyExchangeRoute = predicateSpec ->
                predicateSpec.path("/currency-exchange/**")
                        .uri("lb://currency-exchange-service");
        Function<PredicateSpec, Buildable<Route>> currencyConversionRoute = predicateSpec ->
                predicateSpec.path("/currency-conversion/**")
                        .uri("lb://currency-conversion-service");
        Function<PredicateSpec, Buildable<Route>> currencyConversionFeignRoute = predicateSpec ->
                predicateSpec.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion-service");
        return routeLocatorBuilder.routes()
                .route(routeFunction)
                .route(currencyExchangeRoute)
                .route(currencyConversionRoute)
                .route(currencyConversionFeignRoute)
                .build();
    }
}
