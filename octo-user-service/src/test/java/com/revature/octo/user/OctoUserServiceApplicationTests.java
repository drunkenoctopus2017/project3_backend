package com.revature.octo.user;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.revature.octo.user.controller.BoardController;
import com.revature.octo.user.controller.SystemUserController;
import com.revature.octo.user.model.BoardUserJoin;
import com.revature.octo.user.model.SystemUser;
import com.revature.octo.user.repository.BoardUserJoinRepository;
import com.revature.octo.user.repository.SystemUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OctoUserServiceApplicationTests {

	@Autowired
	SystemUserRepository userRepo;
	
	@Autowired
	BoardUserJoinRepository boardUserRepo;
	
	@Autowired
	SystemUserController sysUserCtrl;
	
	@Autowired
	BoardController boardCtrl;
	
	static boolean userSaveTested;
	
	static boolean boardUserJoinSaveTested;
	
	@BeforeClass
	public static void stuff() {
		userSaveTested = false;
		boardUserJoinSaveTested = false;
	}
	
	// SystemUserController tests
	@Test
	public void testGetBoardIds() {
		List<Object> boardIds = sysUserCtrl.getBoardIds(6);
		int actual = (Integer) boardIds.get(0);
		Assert.assertEquals(29, actual);
	}
	
	@Test
	public void testGetBoardMembersByBoardId() {
		List<SystemUser> users = sysUserCtrl.getBoardMembersByBoardId(29);
		int actual = users.size();
		Assert.assertEquals(6, actual);
	}
	
	@Test
	public void testDeleteBoardIdFromUser() {
		SystemUser su = userRepo.findById(6);
		ResponseEntity<Boolean> resp = sysUserCtrl.deleteBoardIdFromUser(29, su);
		Assert.assertTrue(resp.getBody());
	}
	
	@After
	public void reAddDeletedBoardToBUJs() {
		SystemUser user = userRepo.findById(6);
		boolean containsBoard = false;
		Iterator<BoardUserJoin> it = user.getBoardUserJoins().iterator();
		while(it.hasNext()) {
			if(it.next().getBoardId() == 29) {
				containsBoard = true;
			}
		}
		if(!containsBoard) {
			user.getBoardUserJoins().add(new BoardUserJoin(29,user));
		}
		if(boardUserRepo.getEntriesByBoardId(29).isEmpty()) {
			System.out.println("reAdding stuff");
			user = userRepo.findById(1);
			boardUserRepo.save(new BoardUserJoin(29,user));
			user = userRepo.findById(2);
			boardUserRepo.save(new BoardUserJoin(29,user));
			user = userRepo.findById(3);
			boardUserRepo.save(new BoardUserJoin(29,user));
			user = userRepo.findById(4);
			boardUserRepo.save(new BoardUserJoin(29,user));
			user = userRepo.findById(5);
			boardUserRepo.save(new BoardUserJoin(29,user));
			user = userRepo.findById(6);
			boardUserRepo.save(new BoardUserJoin(29,user));
		}
	}
	
	// SystemUserRepository tests
	@Test
	public void testSysUserRepoFindOne() {
		SystemUser systemUser = userRepo.findOne(6);
		Assert.assertNotNull(systemUser);
		Assert.assertEquals(6, systemUser.getId());
	}
	
	@Test
	public void testSysUserRepoFindById() {
		SystemUser systemUser = userRepo.findById(6);
		Assert.assertNotNull(systemUser);
		Assert.assertEquals(6, systemUser.getId());
	}
	
	@Test
	public void testSysUserRepoFindByBoardUserJoins_boardId() {
		List<SystemUser> systemUserListOnBoard = userRepo.findByBoardUserJoins_boardId(29);
		int actual = systemUserListOnBoard.size();
		Assert.assertEquals(6, actual);
	}
	
	@Test
	public void testSysUserRepoFindAll() {
		List<SystemUser> systemUserList = (List<SystemUser>) userRepo.findAll();
		int actual = systemUserList.size();
		Assert.assertEquals(6, actual);
	}
	
	@Test
	public void testSysUserRepoFindByUsername() {
		SystemUser user = userRepo.findByUsername("u");
		int actual = user.getId();
		Assert.assertEquals(6, actual);
	}
	
	@Test
	public void testSysUserRepoSave() {
		SystemUser user = new SystemUser("WhereAre", "You?");
		user.setFirstName("Dooby");
		user.setLastName("Doo");
		userRepo.save(user);
		SystemUser retrievedUser = userRepo.findByUsername("WhereAre");
		Assert.assertEquals(user.getPassword(), retrievedUser.getPassword());
		userSaveTested = true;
	}
	
	@After
	public void deleteAddedUser() {
		if(userSaveTested == true) {
			System.out.println("deleting added user");
			SystemUser retrievedUser = userRepo.findByUsername("WhereAre");
			userRepo.delete(retrievedUser);
			userSaveTested = false;
		}
	}
	
	// BoardUserJoinRepository tests
	@Test
	public void testBoardUserJoinGetScrumBoardIdsByUser() {
		SystemUser systemUser = userRepo.findOne(6);
		List<Object> boardIds = boardUserRepo.getScrumBoardIdsByUser(systemUser);
		int actual = (Integer) boardIds.get(0);
		Assert.assertEquals(29, actual);
	}
	
	@Test
	public void testBoardUserJoinRepoGetSystemUsersByBoardId() {
		List<SystemUser> boardMembers = boardUserRepo.getSystemUsersByBoardId(29);
		int actual = boardMembers.size();
		Assert.assertEquals(6, actual);
	}
	
	@Test
	public void testBoardUserJoinRepoGetEntriesByBoardId() {
		List<BoardUserJoin> bujEntries = boardUserRepo.getEntriesByBoardId(29);
		int actual = bujEntries.size();
		Assert.assertEquals(6, actual);
	}
	
	@Test
	public void testBoardUserJoinRepoSave() {
		SystemUser user = new SystemUser("Yoshi", "Ikuzo");
		user.setFirstName("Patrick");
		user.setLastName("Taichou");
		userRepo.save(user);
		boardUserRepo.save(new BoardUserJoin(29, user));
		List<BoardUserJoin> bujEntries = boardUserRepo.getEntriesByBoardId(29);
		int actual = bujEntries.size();
		Assert.assertEquals(7, actual);
		boardUserJoinSaveTested = true;
	}
	
	@After
	public void deleteAddedBUJ() {
		if(boardUserJoinSaveTested == true) {
			System.out.println("deleting added BUJ");
			List<BoardUserJoin> bujEntries = boardUserRepo.getEntriesByBoardId(29);
			for(BoardUserJoin buj : bujEntries) {
				if(buj.getSystemUser().getUsername().equals("Yoshi")) {
					boardUserRepo.delete(buj);
					SystemUser user = userRepo.findByUsername("Yoshi");
					userRepo.delete(user);
				}
			}
			boardUserJoinSaveTested = false;
		}
	}
	
	@Test
	public void testBoardUserJoinRepoDelete() {
		List<BoardUserJoin> bujEntries = boardUserRepo.getEntriesByBoardId(29);
		BoardUserJoin buj = bujEntries.get(0);
		boardUserRepo.delete(buj);
		List<BoardUserJoin> bujEntriesAfterDelete = boardUserRepo.getEntriesByBoardId(29);
		int actual = bujEntriesAfterDelete.size();
		Assert.assertEquals(5, actual);
		boardUserRepo.save(buj); //reAdd it to preserve state of DB
	}
	
	@Test 
	public void testBoardUserJoinRepoFindAll() {
		// this unit test is volatile to changes, whether in adding new boards or adding users to boards, 
		// deleting, etc
		List<BoardUserJoin> allBUJEntries = (List<BoardUserJoin>) boardUserRepo.findAll();
		int actual = allBUJEntries.size();
		Assert.assertEquals(11, actual);
	}
	
	
}
