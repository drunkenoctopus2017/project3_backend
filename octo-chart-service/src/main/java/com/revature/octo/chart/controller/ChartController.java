package com.revature.octo.chart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChartController {

	@GetMapping("/")
	public String chartTest() {
		return "Hello Chart";
	}
}
