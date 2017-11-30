package com.revature.octo.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.user.model.SystemUser;
import com.revature.octo.user.repository.SystemUserRepository;

@RestController
public class GetAllUsers {
	
	@Autowired
	SystemUserRepository userRepo;
	
	@GetMapping(path="/addUsers", produces="application/json")
	@ResponseBody
	public List<SystemUser> getAllUsers() {
		return (List<SystemUser>) userRepo.findAll();
	}

}
