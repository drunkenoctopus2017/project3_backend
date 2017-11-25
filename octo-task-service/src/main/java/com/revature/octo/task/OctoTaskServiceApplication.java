package com.revature.octo.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OctoTaskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctoTaskServiceApplication.class, args);
	}
}
