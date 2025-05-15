package com.sdh2.demo;

import org.springframework.boot.SpringApplication;

public class TestDemoprojectApplication {

	public static void main(String[] args) {
		SpringApplication.from(DemoprojectApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
