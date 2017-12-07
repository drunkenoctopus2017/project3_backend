package com.revature.octo.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.user.model.SystemUser;
import com.revature.octo.user.service.SystemUserService;

@RestController
public class SystemUserController {
	
	@Autowired
	SystemUserService sysUserService;
	
	/**
	 * Note: We don't care that the List uses Object for its generic (instead of Integer) because 
	 * we end up parsing it into JSON anyway.
	 * 
	 * @param id
	 * @return List of Integer (Objects) that are then automatically converted to JSON.
	 */
	@GetMapping(path="/getScrumBoardIdsByUserId/{id}")
	public List<Object> getBoardIds(@PathVariable int id) {
		return sysUserService.getBoardIds(id);
	}
	
	@GetMapping(path="/getBoardMembersByBoardId/{id}")
	public List<SystemUser> getBoardMembersByBoardId(@PathVariable int id) {
		return sysUserService.getBoardMembersByBoardId(id);
	}
	
	@PostMapping(path="/deleteScrumBoardIdFromUser/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> deleteBoardIdFromUser(@PathVariable int id, @RequestBody SystemUser su) {
		return sysUserService.deleteBoardIdFromUser(id, su);
	}
}
