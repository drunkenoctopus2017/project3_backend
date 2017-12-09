package com.revature.octo.story.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.story.model.Story;
import com.revature.octo.story.service.StoryService;

//@EnableBinding(Sink.class)
@RestController
public class StoryController {
	
	
	@Autowired
	StoryService storyService;
	
	@GetMapping(path="/getStoriesByBoardId/{boardId}")
	public List<Story> getStoriesByBoardId(@PathVariable int boardId) {
		return storyService.getStoriesByBoardId(boardId);
	}
	
	@GetMapping(path="/getStories")
	public List<Story> getStories() {
		return storyService.getStories();
	}
	
	/**
	 * Used to directly update a story.
	 * This method is deprecated.
	 * Requests to update stories should go through the story update manager 
	 * which will then send an update through RabbitMQ.
	 * 
	 * @param story
	 * @return the updated story
	 */
	@PostMapping(path="/updateStory", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Story updateStory(@RequestBody Story story) {
		return storyService.updateStory(story);
	}
	
	@GetMapping(path="/deleteStoriesByBoardId/{boardId}")
	public String deleteStoriesByBoardId(@PathVariable int boardId) {
		return storyService.deleteStoriesByBoardId(boardId);
	}
	
	@PostMapping(path="/deleteStory", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void deleteStory(@RequestBody Story story) {
		storyService.deleteStory(story);
	}
}
