package com.poc.logging;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.poc.logging")
@Slf4j
public class CustomLoggingApplication {

	public static void main(String[] args) {
		//log.info("Starting CustomLoggingApplication");
		SpringApplication.run(CustomLoggingApplication.class, args);
	}

}
