package com.poc.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.poc.logging")

public class CustomLoggingApplication {
    private static final Logger log = LoggerFactory.getLogger(CustomLoggingApplication.class);

    public static void main(String[] args) {
        log.info("Starting CustomLoggingApplication");
        SpringApplication.run(CustomLoggingApplication.class, args);
    }

}
