package com.revature.octo.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.user.model.OctoUser;
import com.revature.octo.user.repository.OctoUserRepository;

@RestController
public class OctoUserController {
	/*
	 * Old ScrumUser DAO methods
	 * 	public ScrumBoard addUserToBoard(UserBoardDTO ub);
		public List<ScrumBoard> getScrumBoardsByUserId(int userId);
		public ScrumUser getScrumUserById(int userId);
		public ScrumUser getScrumUserByUsername(ScrumUser user);
		public ScrumUser updateScrumUser(ScrumUser su);

	 */
	@Autowired 
	OctoUserRepository userRepo;
	
	@GetMapping("/createOrUpdateNewUser")
	public OctoUser createorUpdateUser(OctoUser user) {
		userRepo.save(user);
		return user;
	}
	
	@GetMapping("/getAllUsers")
	public List<OctoUser> getAllUsers() {
		ArrayList<OctoUser> users = (ArrayList<OctoUser>)userRepo.findAll();
		return users;
	}
	

	
}
