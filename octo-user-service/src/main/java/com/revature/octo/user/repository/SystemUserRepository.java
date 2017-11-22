package com.revature.octo.user.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.octo.user.model.SystemUser;

@Repository
public interface SystemUserRepository extends CrudRepository<SystemUser, Integer> {
	SystemUser findByUsername(String username);
	List<SystemUser> findByBoardUserJoins_boardId(int boardId);
}

