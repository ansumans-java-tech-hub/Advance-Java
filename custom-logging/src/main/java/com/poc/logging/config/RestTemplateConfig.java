package com.poc.logging.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfig {

    @Autowired
    private CorrelationIdInterceptor correlationIdInterceptor;
    //Add the interceptor to the RestTemplate
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .additionalInterceptors(correlationIdInterceptor)
                .build();
    }

}
