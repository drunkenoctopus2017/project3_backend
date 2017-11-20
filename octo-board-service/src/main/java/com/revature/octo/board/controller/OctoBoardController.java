package com.revature.octo.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.board.model.OctoBoard;
import com.revature.octo.board.repository.OctoBoardRepository;

@RestController
public class OctoBoardController {
	/*
	 * public ScrumBoard createNewScrumBoard(ScrumBoard sb);
	 * 	public ScrumBoard addUserToBoard(UserBoardDTO ub);
	 * 	public ScrumBoard getScrumBoardById(int boardId);
	 * public ScrumBoard updateScrumBoard(ScrumBoard sb);
	 * public void deleteScrumBoard(ScrumBoard sb);
	 * public List<ScrumBoard> getAllScrumBoards();
	 * public List<ScrumBoard> getScrumBoardsByUserId(int userId);
	 */
	@Autowired
	OctoBoardRepository boardRepo;
	
	@GetMapping("/createNewBoard")
	public OctoBoard createOrUpdateBoard(OctoBoard board) {
		boardRepo.save(board);
		return board;
	}
	
	@GetMapping("/getAllBoards")
	public List<OctoBoard> getAllOctoBoards() {
		ArrayList<OctoBoard> boards = (ArrayList<OctoBoard>) boardRepo.findAll();
		return boards;
	}
	
	@GetMapping("/getBoardById")
	public OctoBoard getOctoBoardById(int id) {
		OctoBoard board = boardRepo.findOne(id);
		return board;
	}
	
	@GetMapping("/deleteBoard")
	public void deleteOctobard(OctoBoard board) {
		boardRepo.delete(board.getId());
	}
	
	
	@ExceptionHandler
	public void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}
	
}
