package com.revature.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(allowCredentials="true")
@SpringBootApplication
@EnableEurekaClient
public class AuthenticationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServerApplication.class, args);
	}
}
