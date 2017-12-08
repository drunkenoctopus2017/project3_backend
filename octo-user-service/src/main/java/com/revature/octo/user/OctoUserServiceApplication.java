package com.revature.octo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableEurekaClient
@SpringBootApplication
@EnableResourceServer
@EnableCircuitBreaker
public class OctoUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctoUserServiceApplication.class, args);
	}
}
