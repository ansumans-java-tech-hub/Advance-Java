package com.poc.logging.config;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

/**
 * Correlation Id filter class Retrieves or generate the correlation ID
 */
@Component
public class CorrelationIdFilter implements Filter {

    public static final String CORRELATION_ID_HEADER = "X-Correlation-ID";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // Retrieve or generate the correlation ID
        String correlationId = httpRequest.getHeader(CORRELATION_ID_HEADER);
        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = UUID.randomUUID().toString();
        }

        // Add the correlation ID to MDC (for logging)
        MDC.put(CORRELATION_ID_HEADER, correlationId);

        // Set the correlation ID in the response header (for client to see)
        httpResponse.setHeader(CORRELATION_ID_HEADER, correlationId);

        try {
            // Proceed with the request processing
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // Remove the correlation ID from MDC after the request is processed
            MDC.remove(CORRELATION_ID_HEADER);
        }

    }
}
