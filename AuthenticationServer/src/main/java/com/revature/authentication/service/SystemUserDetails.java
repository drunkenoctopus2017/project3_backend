package com.revature.authentication.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SystemUserDetails extends SystemUser implements UserDetails {
	/**
	 * Version 1.0
	 */
	private static final long serialVersionUID = 1L;

	public SystemUserDetails() {}
	
	public SystemUserDetails(SystemUser su) {
		super(su);
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	@Override
	public void setEnabled(boolean isEnabled) {
		super.isEnabled = isEnabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}
	
	@Override
	public void setAccountNonExpired(boolean isAccountNonExpired) {
		super.isAccountNonExpired = isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	
	public void setAccountNonLocked(boolean isAccountNonLocked) {
		super.isAccountNonLocked = isAccountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		super.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("role.getRole()"));
		return authorities;
	}

}