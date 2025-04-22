package com.poc.logging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    /**
     * Function getCorelationID
     *
     */
    @GetMapping("/corelationID")
    public String getCorelationID() {
        logger.info("Processing request in getCorelationID");
        // Log with Correlation ID if available in MDC (from filter)
        String correlationId = MDC.get("X-Correlation-ID");
        if (correlationId != null) {
            logger.info("Correlation ID: {}", correlationId);
        }
        return "Response with Correlation ID";
    }

}



