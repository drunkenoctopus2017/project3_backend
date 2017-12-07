package com.revature.octo.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.octo.user.model.BoardUserJoin;
import com.revature.octo.user.model.SystemUser;
import com.revature.octo.user.repository.BoardUserJoinRepository;
import com.revature.octo.user.repository.SystemUserRepository;

@Service
public class LoginService {
	@Autowired
	SystemUserRepository userRepo;
	
	@Autowired
	BoardUserJoinRepository boardUserRepo;
	
	public SystemUser loginValidation(SystemUser user) {
		return null;
	}
	
	public List<SystemUser> getTestUsers() {
		return (List<SystemUser>) userRepo.findAll();
	}
	
	public List<SystemUser> createTestUsers() {
		System.out.println("Get Mapping -Get Request /getTestUsers");
		ArrayList<SystemUser> userList = new ArrayList<>();
		SystemUser user = userRepo.findByUsername("jpwrunyan");
		if (user == null) {
			user = new SystemUser("jpwrunyan", "jetfuel");
			userRepo.save(user);
			boardUserRepo.save(new BoardUserJoin(1, user));
			System.out.println("creating: " + user);
		} else {
			System.out.println("*user found: " + user);
		}
		userList.add(user);
		user = userRepo.findByUsername("scooby");
		if (user == null) {
			user = new SystemUser("scooby", "doo");
			userRepo.save(user);
			System.out.println("creating: " + user);
		} else {
			System.out.println("*user found: " + user);
		}
		userList.add(user);
		return userList;
	}
	
	public SystemUser login(SystemUser user){

		SystemUser loggedInUser = userRepo.findByUsername(user.getUsername());
		if (loggedInUser.getUsername().equals(user.getUsername()) && loggedInUser.getPassword().equals(user.getPassword())) {
			return loggedInUser;
		}
		
		System.out.println(loggedInUser);

		return loggedInUser;
	}
	
	
}

