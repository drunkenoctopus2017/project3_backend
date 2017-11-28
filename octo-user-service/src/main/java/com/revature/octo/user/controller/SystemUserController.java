package com.revature.octo.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.user.model.SystemUser;
import com.revature.octo.user.repository.BoardUserJoinRepository;
import com.revature.octo.user.repository.SystemUserRepository;

@RestController
public class SystemUserController {
	
	@Autowired
	SystemUserRepository userRepo;
	
	@Autowired
	BoardUserJoinRepository boardUserRepo;
	
	/**
	 * Note: We don't care that the List uses Object for its generic (instead of Integer) because 
	 * we end up parsing it into JSON anyway.
	 * 
	 * @param id
	 * @return List of Integer (Objects) that are then automatically converted to JSON.
	 */
	@GetMapping(path="/getScrumBoardIdsByUserId/{id}")
	public List<Object> getBoardIds(@PathVariable int id) {
		SystemUser systemUser = userRepo.findOne(id);
		List<Object> boardIds = boardUserRepo.getScrumBoardIdsByUser(systemUser);
		return boardIds;
	}
	
	@GetMapping(path="/getBoardMembersByBoardId/{id}")
	public List<SystemUser> getBoardMembersByBoardId(@PathVariable int id) {
		List<SystemUser> boardMembers = boardUserRepo.getSystemUsersByBoardId(id);
		return boardMembers;
	}
}
