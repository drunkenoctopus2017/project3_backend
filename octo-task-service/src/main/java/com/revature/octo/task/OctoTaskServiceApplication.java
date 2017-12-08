package com.revature.octo.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableEurekaClient
@SpringBootApplication
@EnableResourceServer
@EnableCircuitBreaker
public class OctoTaskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctoTaskServiceApplication.class, args);
	}
}
