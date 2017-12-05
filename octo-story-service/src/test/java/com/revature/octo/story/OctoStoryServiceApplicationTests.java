package com.revature.octo.story;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.octo.story.controller.StoryController;
import com.revature.octo.story.repository.StoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OctoStoryServiceApplicationTests {
	
	@Autowired
	StoryRepository storyRepo;
	
	@Autowired
	StoryController storyCtrl;

	@Test
	public void contextLoads() {
	}

}
