package com.revature.octo.user.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.octo.user.model.BoardUserJoin;
import com.revature.octo.user.model.SystemUser;
import com.revature.octo.user.repository.BoardUserJoinRepository;
import com.revature.octo.user.repository.SystemUserRepository;

@Service
public class BoardService {
	@Autowired
	SystemUserRepository userRepo;
	
	@Autowired
	BoardUserJoinRepository boardUserRepo;
	
	public ResponseEntity<String> getBoardIds(String userId) {
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

	public ResponseEntity<List<SystemUser>> getAllUsersOnBoard(String boardId) {
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
	
	public ResponseEntity<List<SystemUser>> getUsersOnBoard(String boardId){
		int boardNum = Integer.parseInt(boardId);
		List<SystemUser> usersOnBoard = (List<SystemUser>)userRepo.findByBoardUserJoins_boardId(boardNum);
		return new ResponseEntity<List<SystemUser>>(usersOnBoard, HttpStatus.OK);
	}
	

	public ResponseEntity<Boolean> updateBoardUsers(String boardId, List<SystemUser> updatedList){
		//printing out the request body
		System.out.println("\n\nupdated list of board users:" + updatedList + "\n\n");
		
		//boardId: String -> Integer
		Integer boardNum = Integer.parseInt(boardId);
		
		//Get the list of board members prior to addition/deletion
		List<SystemUser> currentBoardUsers = (List<SystemUser>) boardUserRepo.getSystemUsersByBoardId(boardNum);
		
		//user ids prior to addition/removal
		List<Integer> oldUserIds = new ArrayList<Integer>();
		for(SystemUser member : currentBoardUsers) {
			oldUserIds.add(member.getId());
		}
		
		//user ids after addition/removal 
		List<Integer> newUserIds = new ArrayList<Integer>();
		for(SystemUser newMember : updatedList) {
			newUserIds.add(newMember.getId());
		}
		
		SystemUser user = null;
		BoardUserJoin buj = null;
		
		//DELETION:
		//if the new list of board ids does NOT contain an id from before addition/deletion took place,
		//delete BoardUserJoin from SystemUser, update boardUserRepo accordingly
		for(Integer i : oldUserIds) {
			if(!newUserIds.contains(i)) {
				user = userRepo.findById(i);
				System.out.println("\n\nUSERS BUJS BEFORE: " + user.getBoardUserJoins());
				Set<BoardUserJoin> bujSet = user.getBoardUserJoins();
				for(BoardUserJoin b : bujSet) {
					if(b.getBoardId() == boardNum) {
						bujSet.remove(b);
						boardUserRepo.delete(b);
						break;
					}
				}
				System.out.println("\n\nUSERS BUJS AFTER: " + user.getBoardUserJoins() + "\n\n");
				System.out.println("\n\nALL BUJS: " + boardUserRepo.findAll() + "\n\n");
			}
		}
		
		//ADDITION:
		//Remove all previously existing board members from newUserIds list
		//now the list will only contain new members' ids
		newUserIds.removeAll(oldUserIds);
		
		//add BoardUserJoins per SystemUser, update boardUserRepo accordingly
		for(Integer i : newUserIds) {
			user = userRepo.findById(i);
			buj = new BoardUserJoin(boardNum, user);
			if(user.getBoardUserJoins() == null) {
				HashSet<BoardUserJoin> bujs = new HashSet<BoardUserJoin>();
				bujs.add(buj);
				user.setBoardUserJoins(bujs);
			}else {
				user.getBoardUserJoins().add(buj);
			}
			boardUserRepo.save(buj);
		}
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
