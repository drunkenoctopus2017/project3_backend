package com.revature.octo.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.revature.octo.user.repository.SystemUserRepository;

@Service(value="systemUserService")
public class SystemUserService implements UserDetailsService {
	
	@Autowired
	SystemUserRepository userRepo;
	
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
}
