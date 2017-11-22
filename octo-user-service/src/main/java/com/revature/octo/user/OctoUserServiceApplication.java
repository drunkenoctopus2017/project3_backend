package com.revature.octo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OctoUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctoUserServiceApplication.class, args);
	}
}
