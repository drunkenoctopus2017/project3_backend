package com.revature.storyupdater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OctoStoryUpdateManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctoStoryUpdateManagerApplication.class, args);
	}
}
