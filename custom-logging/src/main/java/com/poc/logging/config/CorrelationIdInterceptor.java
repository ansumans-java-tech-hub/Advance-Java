package com.poc.logging.config;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Intercepts HTTP requests to add a correlation ID header (X-Correlation-ID) to each outgoing request.
 *
 * This interceptor retrieves the correlation ID from the MDC (Mapped Diagnostic Context).
 * If the correlation ID is not present or empty, it generates a new UUID as the correlation ID.
 * The header, containing the correlation ID, is then added to the outgoing request.
 *
 * It is typically used to trace requests across distributed systems for better observability.
 * This interceptor can be utilized with RestTemplate or WebClient to ensure that each
 * outgoing request carries a unique or pre-existing correlation ID.
 *
 * Implements Spring's ClientHttpRequestInterceptor interface.
 */

@Component
public class CorrelationIdInterceptor implements ClientHttpRequestInterceptor {

    private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";

    /**
     * Intercepts an HTTP request to add a correlation ID header (X-Correlation-ID) to the outgoing request.
     *
     * This method retrieves the correlation ID from the MDC (Mapped Diagnostic Context). If the correlation ID
     * is not present or is empty, it generates a new UUID as the correlation ID. The correlation ID is then
     * added as a header to the HTTP request. Finally, the modified request is executed.
     *
     * @param request  the HTTP request being intercepted
     * @param body     the body of the HTTP request
     * @param execution the HTTP request execution, allowing further interception or final execution of the request
     * @return the response from the executed HTTP request
     * @throws IOException if an I/O error occurs while processing the HTTP request
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String correlationId = MDC.get(CORRELATION_ID_HEADER);
            if (correlationId == null || correlationId.isEmpty()) {
                correlationId = String.valueOf(java.util.UUID.randomUUID());
            }
            request.getHeaders().add(CORRELATION_ID_HEADER, correlationId);
            return execution.execute(request, body);


    }
}