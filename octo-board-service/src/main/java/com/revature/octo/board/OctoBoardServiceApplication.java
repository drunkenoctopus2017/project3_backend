package com.revature.octo.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OctoBoardServiceApplication {
	//Test
	public static void main(String[] args) {
		SpringApplication.run(OctoBoardServiceApplication.class, args);
	}
}
