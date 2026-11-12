package com.spring.spring_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the Student REST API application.
 * {@code @SpringBootApplication} turns on auto-configuration and
 * component scanning, so Spring finds the controllers, services, and
 * repositories in this package and its sub-packages.
 */
@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

}
