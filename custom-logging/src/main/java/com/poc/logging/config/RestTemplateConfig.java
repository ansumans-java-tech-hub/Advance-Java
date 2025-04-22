package com.poc.logging.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Build Rest Template with correlationIdInterceptor
 */
@Configuration
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
