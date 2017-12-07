package com.revature.octo.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.board.model.ScrumBoard;
import com.revature.octo.board.service.ScrumBoardService;

@RestController
public class ScrumBoardController {
	
	@Autowired 
	ScrumBoardService boardService;
	
	@PostMapping(path="/getScrumBoardIdAndNameByIds", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Object> getBoardIdAndName(@RequestBody List<Integer> boardIds) {
		return boardService.getBoardIdAndName(boardIds);
	}
	
	@PostMapping(path="/getBoardsByIds", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ScrumBoard> getScrumBoardsByIds(@RequestBody List<Integer> boardIds) {
		return boardService.getScrumBoardsByIds(boardIds);
	}
	
	@GetMapping(path="/getBoardById/{id}")
	public ScrumBoard getBoardById(@PathVariable int id) {
		return boardService.getBoardById(id);
	}
	
	@GetMapping("/getAllBoards")
	public List<ScrumBoard> getAllBoards() {
		return boardService.getAllBoards();
	}
	
	@GetMapping("/createTestBoards")
	public List<ScrumBoard> createTestBoards() {
		return boardService.createTestBoards();
	}
	
	@PostMapping(path="/createUpdateBoard",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ScrumBoard createUpdateBoard(@RequestBody ScrumBoard sb) {
		return boardService.createUpdateBoard(sb);
	}
	
	@GetMapping(path="/deleteBoardById/{id}")
	public String deleteBoardById(@PathVariable int id) {
		return boardService.deleteBoardById(id);
	}
}
