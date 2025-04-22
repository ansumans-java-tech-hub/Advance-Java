package com.poc.logging.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CorrelationIdInterceptorTest {

    private CorrelationIdInterceptor correlationIdInterceptor;

    @BeforeEach
    public void setup() {
        correlationIdInterceptor = new CorrelationIdInterceptor();
    }

    @Test
    public void testInterceptorUsesMDCIfPresent() throws IOException {

        String expectedCorrelationId = "test-mdc-id";
        MDC.put("X-Correlation-ID", expectedCorrelationId);

        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpHeaders headers = new HttpHeaders();
        when(mockRequest.getHeaders()).thenReturn(headers);
        when(mockRequest.getMethod()).thenReturn(HttpMethod.GET);
        when(mockRequest.getURI()).thenReturn(URI.create("http://test.com"));

        ClientHttpRequestExecution mockExecution = mock(ClientHttpRequestExecution.class);
        when(mockExecution.execute(any(), any()))
                .thenReturn(mock(ClientHttpResponse.class));

        // Act
        correlationIdInterceptor.intercept(mockRequest, new byte[0], mockExecution);

        // Assert
        assertThat(headers.getFirst("X-Correlation-ID")).isEqualTo(expectedCorrelationId);

        // Cleanup
        MDC.clear();
    }

    @Test
    public void testInterceptorGeneratesCorrelationIdIfMDCIsMissing() throws IOException {
        // Arrange
        MDC.clear(); // Simulate no correlation ID in MDC

        HttpRequest mockRequest = mock(HttpRequest.class);
        HttpHeaders headers = new HttpHeaders();
        when(mockRequest.getHeaders()).thenReturn(headers);
        when(mockRequest.getMethod()).thenReturn(HttpMethod.GET);
        when(mockRequest.getURI()).thenReturn(URI.create("http://test.com"));

        ClientHttpRequestExecution mockExecution = mock(ClientHttpRequestExecution.class);
        when(mockExecution.execute(any(), any()))
                .thenReturn(mock(ClientHttpResponse.class));

        // Act
        correlationIdInterceptor.intercept(mockRequest, new byte[0], mockExecution);

        // Assert header is not blank
        String header = headers.getFirst("X-Correlation-ID");
        assertThat(header).isNotBlank(); // Must be generated
    }
}

