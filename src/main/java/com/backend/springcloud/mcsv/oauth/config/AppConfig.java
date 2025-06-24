package com.backend.springcloud.mcsv.oauth.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    /*@Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder().baseUrl("http://USERS-MICROSERVICES-SPRING-CLOUD");
    }*/

    @Bean
    WebClient webClient(WebClient.Builder webClientBuilder, ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        return webClientBuilder.baseUrl("http://USERS-MICROSERVICES-SPRING-CLOUD").filter(lbFunction).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
