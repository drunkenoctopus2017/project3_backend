package com.revature.octo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.user.model.SystemUser;
import com.revature.octo.user.service.SystemUserService;


@RestController
public class LoginController {
	
	@Autowired
	SystemUserService service;
	
	@GetMapping("/")
	public String helloBoot() {
		System.out.println("Get Mapping -Get Request /");
		return new String("Helooo!!");
	}
	
	@PostMapping(path="/login", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public SystemUser login(@RequestBody SystemUser user){
//		System.out.println("sissy: " + user.toString());
		SystemUser loggedInUser = service.loginValidation(user);
		System.out.println(loggedInUser);
		return loggedInUser;
	}
	
	//----The following may be moved to the data service----//
	
	/*
	@Autowired
	SystemUserRepository userRepo;
	
	@GetMapping("/test")
	public SystemUser getUser() {
		
		Iterator<SystemUser> it = userRepo.findAll().iterator();
		
		for (SystemUser su : userRepo.findAll()) {
			su.toString();
		}
		
		while (it.hasNext()) {
			System.err.println(it.next());
		}
		
		int findId = 1;
		SystemUser user;
		if (userRepo.exists(findId)) {
			user = userRepo.findOne(findId);
			System.out.println("Found user: " + user);
		} else {
			user = new SystemUser();
			user.setId(findId);
			user.setFirstName("Demo");
			user.setLastName("User");
			user.setPassword("password");
			user.setUsername("demouser");
			user = userRepo.save(user);
			System.out.println("Created new user: " + user);
		}
		return user;
		//SystemUser testUser = userRepo.findOne(1);
		//System.out.println("testUser: " + testUser.toString());
		//return testUser;
	}
	
	
	@GetMapping("/getAllUsers")
	//@Transactional
	public List<SystemUser> getAllUsers() {
		
		int findId = 1;
		
		List<SystemUser> users = new ArrayList<>();
		
		
		//if (!userRepo.exists(findId)) {
			SystemUser newUser = new SystemUser();
			newUser.setId(findId++);
			newUser.setFirstName("Demo");
			newUser.setLastName("User");
			newUser.setUsername("nullname");
			newUser.setPassword("password");
			//newUser = userRepo.save(newUser);
			//System.out.println("Created new user: " + newUser);
			//return newUser;
		//}
			
		users.add(newUser);
		
		newUser = new SystemUser();
		newUser.setId(findId++);
		newUser.setFirstName("Patrick");
		newUser.setLastName("Runyan");
		newUser.setUsername("jpwrunyan");
		newUser.setPassword("jetfuel");
		users.add(newUser);
		
		newUser = new SystemUser();
		newUser.setId(findId++);
		newUser.setFirstName("Jibril");
		newUser.setLastName("Burleigh");
		newUser.setUsername("thisthatthethird");
		newUser.setPassword("b");
		users.add(newUser);
		
		newUser = new SystemUser();
		newUser.setId(findId++);
		newUser.setFirstName("Ben");
		newUser.setLastName("Rogers");
		newUser.setUsername("brogers");
		newUser.setPassword("g00dpassword");
		users.add(newUser);
		
		newUser = new SystemUser();
		newUser.setId(findId++);
		newUser.setFirstName("Elvis");
		newUser.setLastName("Yang");
		newUser.setUsername("waveeyang");
		newUser.setPassword("picksomethingIdontknow");
		users.add(newUser);
		
		//SystemUser testUser = userRepo.findOne(1);
		//System.out.println("testUser: " + testUser.toString());
		//return testUser;
		System.out.println("users: " + users);
		return users;
	}
	
	@PostMapping(path="/addUser", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public SystemUser addUser(@RequestBody SystemUser user){
		user.setId((int) (new Date().getTime() % Integer.MAX_VALUE));
		List<SystemUser> allUsers = this.getAllUsers();
		allUsers.add(user);
		
		return user;
	}
	*/
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e){
		e.printStackTrace();
		return new ResponseEntity<Exception>(e, HttpStatus.CONFLICT);
	}
}
