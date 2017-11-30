package com.revature.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.authentication.service.SystemUser;

public interface SystemUserDetailsRepo extends JpaRepository<SystemUser, Integer>{
	Optional<SystemUser> findByUsername(String username);
}
