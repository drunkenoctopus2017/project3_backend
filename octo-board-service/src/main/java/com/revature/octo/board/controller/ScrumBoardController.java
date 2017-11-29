package com.revature.octo.board.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.board.model.ScrumBoard;
import com.revature.octo.board.repository.ScrumBoardRepository;

@RestController
public class ScrumBoardController {
	
	@Autowired
	ScrumBoardRepository boardRepo;
	
	@PostMapping(path="/getScrumBoardIdAndNameByIds", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Object> getBoardIdAndName(@RequestBody List<Integer> boardIds) {
		return boardRepo.getScrumBoardIdAndNameByIds(boardIds);
	}
	
	@PostMapping(path="/getBoardsByIds", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ScrumBoard> getScrumBoardsByIds(@RequestBody List<Integer> boardIds) {
		return boardRepo.findByIdIn(boardIds);
	}
	
	@GetMapping(path="/getBoardById/{id}")
	public ScrumBoard getBoardById(@PathVariable int id) {
		ScrumBoard board = boardRepo.findOne(id);
		return board;
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
	
	@PostMapping(path="/createUpdateBoard",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ScrumBoard createUpdateBoard(@RequestBody ScrumBoard sb) {
		return boardRepo.save(sb);
	}

}
