package com.revature.octo.user.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.octo.user.model.BoardUserJoin;
import com.revature.octo.user.model.SystemUser;
import com.revature.octo.user.repository.BoardUserJoinRepository;

//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.revature.octo.user.repository.SystemUserRepository;

@Service(value="systemUserService")
public class SystemUserService {//implements UserDetailsService {
	
	@Autowired
	SystemUserRepository userRepo;
	
	@Autowired
	BoardUserJoinRepository boardUserRepo;
	
	public SystemUser loginValidation(SystemUser user) {
		SystemUser dbUser = userRepo.findByUsername(user.getUsername());
		if(dbUser.getUsername().equals(user.getUsername()) && dbUser.getPassword().equals(user.getPassword())) {
			return dbUser;
		}
		return null;
	}

	/**
	 * Note: We don't care that the List uses Object for its generic (instead of Integer) because 
	 * we end up parsing it into JSON anyway.
	 * 
	 * @param id
	 * @return List of Integer (Objects) that are then automatically converted to JSON.
	 */
	public List<Object> getBoardIds(int id) {
		SystemUser systemUser = userRepo.findOne(id);
		List<Object> boardIds = boardUserRepo.getScrumBoardIdsByUser(systemUser);
		return boardIds;
	}
	
	public List<SystemUser> getBoardMembersByBoardId(int id) {
		List<SystemUser> boardMembers = boardUserRepo.getSystemUsersByBoardId(id);
		return boardMembers;
	}
	

	public ResponseEntity<Boolean> deleteBoardIdFromUser(int id, @RequestBody SystemUser su) {
		// get current user
		SystemUser user = userRepo.findById(su.getId());
		// remove BUJ corresponding to this board from User's set of BUJ's
		Iterator<BoardUserJoin> it = user.getBoardUserJoins().iterator();
		while(it.hasNext()) {
			if(it.next().getBoardId() == id) {
				it.remove();
			}
		}
		// find all BUJ's associated with this board and delete them
		List<BoardUserJoin> bujEntries = boardUserRepo.getEntriesByBoardId(id);
		for(BoardUserJoin buj : bujEntries) {
			boardUserRepo.delete(buj);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	public List<SystemUser> getAllUsers() {
		return (List<SystemUser>) userRepo.findAll();
	}

	
	/*
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//TodoUser dbUser = userRepo.findByUsername(username);
		//Spring Security doesn't take your TodoUser pojo, it wants a Spring Security User
		//We need to convert our Application User (TodoUser) to a Spring Security User
		
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		boolean isEnabled = true;
		
		User user = new User(
					"test",
					"test",
					isEnabled,
					accountNonExpired,
					credentialsNonExpired,
					accountNonLocked,
					getAuthorities("role")
				);
		return user;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(String roles) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRolesAsList(roles));
		System.out.println("Auth list: " + authList);
		return authList;
	}
	
	public List<String> getRolesAsList(String roles) {
		List <String> rolesAsList = new ArrayList<String>();
		rolesAsList.add("ROLE_USER");
		return rolesAsList;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
	*/
}
