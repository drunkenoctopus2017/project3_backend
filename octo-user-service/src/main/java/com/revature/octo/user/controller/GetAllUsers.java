package com.revature.octo.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		System.out.println("GetMapping -getAllUsers");
		List<SystemUser> users = (List<SystemUser>) userRepo.findAll();
		System.out.println(users);
		return users;
		
	}

}
