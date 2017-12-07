package com.revature.octo.board.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.octo.board.model.ScrumBoard;
import com.revature.octo.board.repository.ScrumBoardRepository;

@Service
public class ScrumBoardService {
	@Autowired
	ScrumBoardRepository boardRepo;
	
	public List<Object> getBoardIdAndName(List<Integer> boardIds) {
		return boardRepo.getScrumBoardIdAndNameByIds(boardIds);
	}
	
	public List<ScrumBoard> getScrumBoardsByIds(List<Integer> boardIds) {
		return boardRepo.findByIdIn(boardIds);
	}
	
	public ScrumBoard getBoardById(int id) {
		ScrumBoard board = boardRepo.findOne(id);
		return board;
	}
	
	public List<ScrumBoard> getAllBoards() {
		return (List<ScrumBoard>) boardRepo.findAll();
	}
	
	public ScrumBoard createUpdateBoard( ScrumBoard sb) {
		return boardRepo.save(sb);
	}
	
	
	public String deleteBoardById(int id) {
		System.out.println("Id of board to be deleted"+id);
		boardRepo.delete(id);
		System.out.println("deleted board? "+id);
		return "successfully deleted this board: "+id;
	}
	
	public ResponseEntity<List<ScrumBoard>> getBoardList(String boardIds, HttpServletRequest request) {
		List<String> boardIdStringList = new ArrayList<String>(Arrays.asList(boardIds.split(",")));
		ArrayList<Integer> boardIdList = new ArrayList<Integer>();
		for (String boardId : boardIdStringList) {
			boardIdList.add(Integer.parseInt(boardId.trim()));
		}
		List<ScrumBoard> boardList = boardRepo.findByIdIn(boardIdList);
		//List<Board> findByBoardIdIn(ArrayList<Integer> boardList) <- in repo
		return new ResponseEntity<List<ScrumBoard>>(boardList, HttpStatus.OK);
		
	}
	
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
