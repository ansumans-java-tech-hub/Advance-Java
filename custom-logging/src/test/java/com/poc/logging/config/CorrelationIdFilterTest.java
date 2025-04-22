package com.poc.logging.config;

import com.poc.logging.controller.CustomLoggingController;
import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class CorrelationIdFilterTest {
    @Autowired
    private CorrelationIdFilter correlationIdFilter;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Set up MockMvc with the filter
        mockMvc = MockMvcBuilders.standaloneSetup(new CustomLoggingController())
                .addFilters(correlationIdFilter)
                .build();
    }

    @Test
    public void testCorrelationIdFilter() throws Exception {
        String correlationId = UUID.randomUUID().toString();
        // Create a mock mockHttpServletRequest
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.addHeader("X-Correlation-ID", correlationId);
        // Mock response
        MockHttpServletResponse response = new MockHttpServletResponse();
        // Mock FilterChain to simulate mockHttpServletRequest processing
        FilterChain chain = mock(FilterChain.class);
        // Call the filter
        correlationIdFilter.doFilter(mockHttpServletRequest, response, chain);
        // Assert the correlation ID is in the response header
        assertThat(response.getHeader("X-Correlation-ID")).isEqualTo(correlationId);
        // verify that FilterChain was called
        verify(chain).doFilter(mockHttpServletRequest, response);
    }

    @Test
    public void testCorrelationIdFilterGenerated() throws Exception {
        // Create a mock mockHttpServletRequest and mockHttpServletResponse with no Correlation ID
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        // Mock FilterChain to simulate mockHttpServletRequest processing
        FilterChain chain = mock(FilterChain.class);
        // Call the filter
        correlationIdFilter.doFilter(mockHttpServletRequest, mockHttpServletResponse, chain);
        // Assert the correlation ID is generated and added to the mockHttpServletResponse header
        assertThat(mockHttpServletResponse.getHeader("X-Correlation-ID")).isNotEmpty();
        // Make sure the FilterChain was called
        verify(chain).doFilter(mockHttpServletRequest, mockHttpServletResponse);
    }
}
