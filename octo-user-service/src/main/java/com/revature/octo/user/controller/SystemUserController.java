package com.revature.octo.user.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.user.model.BoardUserJoin;
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
	
	@PostMapping(path="/deleteScrumBoardIdFromUser/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> deleteBoardIdFromUser(@PathVariable int id, @RequestBody SystemUser su) {
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
}
