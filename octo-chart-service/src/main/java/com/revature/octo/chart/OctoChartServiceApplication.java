package com.revature.octo.chart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EntityScan(basePackages="com.revature")
@SpringBootApplication
public class OctoChartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctoChartServiceApplication.class, args);
	}
}
