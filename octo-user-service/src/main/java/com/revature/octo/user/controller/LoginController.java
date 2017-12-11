package com.revature.octo.user.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.user.model.BoardUserJoin;
import com.revature.octo.user.model.SystemUser;
import com.revature.octo.user.repository.BoardUserJoinRepository;
import com.revature.octo.user.repository.SystemUserRepository;
import com.revature.octo.user.service.SystemUserService;


@RestController
public class LoginController {
	
	@Autowired
	SystemUserRepository userRepo;
	
	@Autowired
	BoardUserJoinRepository boardUserRepo;
	
	public SystemUser loginValidation(SystemUser user) {
		return null;
	}
	
	@GetMapping("/")
	public String helloBoot() {
		System.out.println("Get Mapping -Get Request /");
		return new String("Helooo!!");
	}
	
	@GetMapping("/getTestUsers")
	public List<SystemUser> getTestUsers() {
		return (List<SystemUser>) userRepo.findAll();
	}
	
	@GetMapping("/createTestUsers")
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
	
	@PostMapping(path="/login", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public SystemUser login(@RequestBody SystemUser user){

		SystemUser loggedInUser = userRepo.findByUsername(user.getUsername());
		if (loggedInUser.getUsername().equals(user.getUsername()) && loggedInUser.getPassword().equals(user.getPassword())) {
			return loggedInUser;
		}
		
		System.out.println(loggedInUser);
		
		loggedInUser.setPassword("Password Not Shown.");

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
