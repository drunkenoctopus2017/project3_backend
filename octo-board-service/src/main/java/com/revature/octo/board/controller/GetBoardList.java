package com.revature.octo.board.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.octo.board.model.ScrumBoard;
import com.revature.octo.board.repository.ScrumBoardRepository;

public class GetBoardList {
	@Autowired
	ScrumBoardRepository boardRepo;
	
	@PostMapping(path="/getBoards", consumes="application/json", produces="application/json") 
	public ResponseEntity<List<ScrumBoard>> getBoardList(@RequestBody String boardIds, HttpServletRequest request) {
		List<String> boardIdStringList = new ArrayList<String>(Arrays.asList(boardIds.split(",")));
		ArrayList<Integer> boardIdList = new ArrayList<Integer>();
		for (String boardId : boardIdStringList) {
			boardIdList.add(Integer.parseInt(boardId.trim()));
		}
		List<ScrumBoard> boardList = boardRepo.findByIdIn(boardIdList);
		//List<Board> findByBoardIdIn(ArrayList<Integer> boardList) <- in repo
		return new ResponseEntity<List<ScrumBoard>>(boardList, HttpStatus.OK);
		
	}
}
