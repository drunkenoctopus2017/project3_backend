package com.revature.octo.board;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.octo.board.model.ScrumBoard;
import com.revature.octo.board.repository.ScrumBoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OctoBoardServiceApplicationTests {

	@Autowired 
	private ScrumBoardRepository boardRepoMock;
	
	@Before
	public void setUp() {
		boardRepoMock = mock(ScrumBoardRepository.class);
	}
	
	@Test
	public void testFindByName() {
		ScrumBoard board = new ScrumBoard();
		when(boardRepoMock.findByName()).thenReturn(board);
	}
}
