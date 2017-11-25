package com.revature.octo.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.user.model.BoardUserJoin;
import com.revature.octo.user.model.SystemUser;
import com.revature.octo.user.repository.SystemUserRepository;

@RestController
public class BoardController {
	
	@Autowired
	SystemUserRepository userRepo;

	@GetMapping(path="/getBoardsForUser/{userId}")
	public ResponseEntity<String> getBoardIds(@PathVariable String userId, HttpServletRequest request) {
		int userNum = Integer.parseInt(userId);
		SystemUser systemUser = userRepo.findOne(userNum);
		Set<BoardUserJoin> boardSet = systemUser.getBoardUserJoins();
		String boardList = "";
		for (BoardUserJoin buj : boardSet) {
			if (boardList.length() > 0) {
				boardList += ",";
			}
			boardList += buj.getBoardId();
		}
		return new ResponseEntity<String>(boardList, HttpStatus.OK);
	}

	@GetMapping(path="/getAllUserNotOnBoard/{boardId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SystemUser>> getAllUsersOnBoard(@PathVariable String boardId, HttpServletRequest request) {
		int boardNum = Integer.parseInt(boardId);
		System.out.println("boardId: " + boardId);
		
		List<SystemUser> systemUserListOnBoard = userRepo.findByBoardUserJoins_boardId(boardNum);
		System.out.println("users on baord: " + systemUserListOnBoard.size());
		
		List<SystemUser> systemUserList = (List<SystemUser>) userRepo.findAll();
		
		System.out.println("users NOT on board: " + systemUserListOnBoard.size());
		
		for (SystemUser su : systemUserListOnBoard) {
			systemUserList.remove(su);
		}
		System.out.println("Final: " + systemUserList);
		return new ResponseEntity<List<SystemUser>>(systemUserList, HttpStatus.OK);
		
	}
	
	@GetMapping(path="/getUsersOnBoard/{boardId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SystemUser>> getUsersOnBoard(@PathVariable String boardId){
		int boardNum = Integer.parseInt(boardId);
		List<SystemUser> usersOnBoard = (List<SystemUser>)userRepo.findByBoardUserJoins_boardId(boardNum);
		return new ResponseEntity<List<SystemUser>>(usersOnBoard, HttpStatus.OK);
	}
	
	@PostMapping(path="/updateBoardUsers/{boardId}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<SystemUser>> updateBoardUsers(@PathVariable String boardId, @RequestBody List<BoardUserJoin> boardUsers){
		System.out.println(boardUsers);
		
		return new ResponseEntity<List<SystemUser>>(new ArrayList<SystemUser>(), HttpStatus.OK);
	}
}
