package com.revature.octo.story.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class CustomHealthCheck implements HealthIndicator {
	int errorcode = -1;
	
	@Override
	public Health health() {
		System.out.println("Health check performed, error code: " + errorcode);
		if(errorcode < 1) {
			errorcode++;
			return Health.down().withDetail("custom Error Code", errorcode).build();
		} else {
			errorcode++;
			return Health.up().build();
		}
		
	}
}
