package com.revature.random.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomController {
	
	@GetMapping("/getRandom")
	//@Transactional
	public String getRandom() {
		int i = (int) Math.floor(14 * Math.random());
		StringBuilder sb = new StringBuilder();
		switch (i) {
			case 0: sb.append("You're a zero."); break;
			case 1: sb.append("You're number one."); break;
			case 2: sb.append("You're too much."); break;
			case 3: sb.append("Three's company."); break;
			case 4: sb.append("For whom the bell tolls..."); break;
			case 5: sb.append("Five times a lady."); break;
			//case 6: sb.append("Six seven eight nine ten, eleven-twelve."); break;
			case 7: sb.append("Lucky number seven."); break;
			case 8: sb.append("You're behind the eight ball."); break;
			case 10: sb.append("Sometimes, you gotta pretend everything is okay."); break;
			case 13: sb.append("There are thirteen ways of looking at blackbirds."); break;
			default: sb.append("Bravely default: " + i);
		}
		return sb.toString();
	}

}
