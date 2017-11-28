package com.revature.octo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class OctoConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctoConfigApplication.class, args);
	}
}
