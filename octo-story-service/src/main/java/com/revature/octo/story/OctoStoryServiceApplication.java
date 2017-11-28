package com.revature.octo.story;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OctoStoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctoStoryServiceApplication.class, args);
	}
}
