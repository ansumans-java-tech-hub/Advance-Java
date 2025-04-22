package com.poc.logging.controller;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomLoggingControllerTest {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(CustomLoggingController.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCorrelationIdLogging() throws Exception {
        // Set up an appender to capture the logs
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.setContext(context);
        listAppender.start();
        // Attach the appender to the logger
        logger.addAppender(listAppender);
        // Make a request to the controller
        mockMvc.perform(get("/correlation-id")
                        .header("X-Correlation-ID", "test-logging-correlation-id"))
                .andExpect(status().isOk());
        // Get the log events captured
        listAppender.stop();
        List<ILoggingEvent> logs = listAppender.list;
        // Assert that the correlation ID is logged
        assertThat(logs)
                .anyMatch(log -> log.getFormattedMessage().contains("test-logging-correlation-id"));
        // Remove appender after the test
        logger.detachAppender(listAppender);
    }


}
