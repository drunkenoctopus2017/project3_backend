package com.revature.octo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class OctoEureka2Application {

	public static void main(String[] args) {
		SpringApplication.run(OctoEureka2Application.class, args);
	}
}
