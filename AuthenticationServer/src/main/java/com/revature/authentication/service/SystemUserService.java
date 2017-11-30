package com.revature.authentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.revature.authentication.repository.SystemUserDetailsRepo;

@Service("systemUserService")
public class SystemUserService implements UserDetailsService {

	@Autowired
	SystemUserDetailsRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<SystemUser> optUser = userRepo.findByUsername(username);
		optUser.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return optUser.map(SystemUserDetails::new).get();
	}

}
