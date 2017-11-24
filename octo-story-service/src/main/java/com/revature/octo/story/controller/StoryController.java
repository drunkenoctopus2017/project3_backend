package com.revature.octo.story.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.story.model.Story;
import com.revature.octo.story.repository.StoryRepository;

@RestController
public class StoryController {
	
	@Autowired
	StoryRepository storyRepo;
	
	@GetMapping(path="/getStoriesByBoardId/{boardId}")
	public List<Story> getStoriesByBoardId(@PathVariable int boardId) {
		return storyRepo.findByBoardId(boardId);
	}
	
	@GetMapping(path="/getStories")
	public List<Story> getStories() {
		return (List<Story>) storyRepo.findAll();
	}
	
	@GetMapping(path="/createStories")
	public List<Story> createStories() {
		List<Story> returnList = new ArrayList<>();
		String[] storyNames = new String[] {"As a user I can login", "As a manager I can login", "As a user I can check my profile", "As a manager I can view all users"};
		for (String storyName : storyNames) {
			Story story = storyRepo.findByName(storyName);
			if (story == null) {
				story = new Story(1, 10, storyName, 10, "");
				storyRepo.save(story);
			}
			returnList.add(story);
		}
		return returnList;
	}
}
