package com.revature.octo.board.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.board.model.ScrumBoard;
import com.revature.octo.board.repository.ScrumBoardRepository;

@RestController
public class ScrumBoardController {
	
	@Autowired
	ScrumBoardRepository boardRepo;
	
	@GetMapping("/testResponse")
	public String testResponse() {
		return "end point works " + new Date().toString();
	}
	
	@GetMapping("/getAllBoards")
	public List<ScrumBoard> getAllBoards() {
		return (List<ScrumBoard>) boardRepo.findAll();
	}
	
	@GetMapping("/createTestBoards")
	public List<ScrumBoard> createTestBoards() {
		ArrayList<ScrumBoard> boardList = new ArrayList<>();
		ScrumBoard board = boardRepo.findByName("Board #1");
		if (board == null) {
			board = new ScrumBoard("Board #1", new Date(), 14);
			boardRepo.save(board);
			System.out.println("creating: " + board);
		} else {
			System.out.println("*board found: " + board);
		}
		boardList.add(board);
		
		board = boardRepo.findByName("Project 2 Sprint 3");
		if (board == null) {
			board = new ScrumBoard("Project 2 Sprint 3", new Date(), 14);
			boardRepo.save(board);
			System.out.println("creating: " + board);
		} else {
			System.out.println("*board found: " + board);
		}
		boardList.add(board);
		
		return boardList;
	}
}
