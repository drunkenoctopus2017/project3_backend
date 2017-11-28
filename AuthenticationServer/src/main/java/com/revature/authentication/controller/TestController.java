package com.revature.authentication.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class TestController {
	public Principal user(Principal user) {
		return user;
	}

}
