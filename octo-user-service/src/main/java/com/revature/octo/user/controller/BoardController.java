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
import com.revature.octo.user.service.BoardService;

@RestController
public class BoardController {
	
	@Autowired
	BoardService boardService;

	@GetMapping(path="/getBoardsForUser/{userId}")
	public ResponseEntity<String> getBoardIds(@PathVariable String userId) {
		return boardService.getBoardIds(userId);
	}

	@GetMapping(path="/getAllUserNotOnBoard/{boardId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SystemUser>> getAllUsersOnBoard(@PathVariable String boardId) {
		return boardService.getAllUsersOnBoard(boardId);
		
	}
	
	@GetMapping(path="/getUsersOnBoard/{boardId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SystemUser>> getUsersOnBoard(@PathVariable String boardId){
		return boardService.getUsersOnBoard(boardId);
	}
	
	@PostMapping(path="/updateBoardUsers/{boardId}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> updateBoardUsers(@PathVariable String boardId, @RequestBody List<SystemUser> updatedList){
		return boardService.updateBoardUsers(boardId, updatedList);
	}
}
