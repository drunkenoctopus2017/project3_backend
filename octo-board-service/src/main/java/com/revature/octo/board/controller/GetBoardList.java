package com.revature.octo.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.octo.board.model.ScrumBoard;
import com.revature.octo.board.service.ScrumBoardService;

public class GetBoardList {
	@Autowired 
	ScrumBoardService boardService;
	
	@PostMapping(path="/getBoards", consumes="application/json", produces="application/json") 
	public ResponseEntity<List<ScrumBoard>> getBoardList(@RequestBody String boardIds, HttpServletRequest request) {
		return boardService.getBoardList(boardIds, request);
	}
}
