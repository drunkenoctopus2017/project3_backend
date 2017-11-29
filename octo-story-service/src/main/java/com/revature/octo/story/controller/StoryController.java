package com.revature.octo.story.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping(path="/updateStory", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Story updateStory(@RequestBody Story story) {
		return storyRepo.save(story);
	}
	
	@GetMapping(path="/deleteStoriesByBoardId/{boardId}")
	public void deleteStoriesByBoardId(@PathVariable int boardId) {
		List<Story> toBeDeleted = storyRepo.findByBoardId(boardId);
		for(Story s : toBeDeleted) {
			storyRepo.delete(s);
		}
	}
}
