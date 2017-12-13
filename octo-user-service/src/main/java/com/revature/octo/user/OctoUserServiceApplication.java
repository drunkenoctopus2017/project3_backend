package com.revature.octo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@EnableResourceServer
@RefreshScope
@RestController
/**
 * 
 * Add @RefreshScrope and @RestController for the spring bus to work.
 * Spring bus adds a refresh endpoint /refresh that is called by config server.
 * This is triggered by config server's /monitor endpoint which is called by the github webhook.
 * 
 * AKA magic
 * 
 * 
 * @author jpwru
 *
 */
public class OctoUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctoUserServiceApplication.class, args);
	}
}
