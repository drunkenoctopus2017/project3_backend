package com.revature.octo.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.user.model.SystemUser;
import com.revature.octo.user.service.SystemUserService;

@RestController
public class GetAllUsers {
	
	@Autowired
	SystemUserService sysUserService;
	
	@GetMapping(path="/addUsers", produces="application/json")
	@ResponseBody
	public List<SystemUser> getAllUsers() {
		return sysUserService.getAllUsers();
	}

}
