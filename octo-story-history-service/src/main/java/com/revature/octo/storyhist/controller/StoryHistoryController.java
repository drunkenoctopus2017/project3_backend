package com.revature.octo.storyhist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.storyhist.model.StoryProfile;
import com.revature.octo.storyhist.service.StoryHistoryService;

@EnableBinding(Sink.class)
@RestController
public class StoryHistoryController {

	@Autowired
	StoryHistoryService storyHistService;
	
	/**
	 * When a story is either created or updated, persist it in the history.
	 * This is called by StoryController in the Story management micro-service.
	 * 
	 * @param message
	 */
	@StreamListener(target=Sink.INPUT)
	public void updateStoryHistory(Map<String, Object> message) {
		storyHistService.updateStoryHistory(message);
	}
	
	@GetMapping("/")
	public String chartTest() {
		return "Hello Chart";
	}
	
	@GetMapping("/getAllStoryProfiles") 
	public List<StoryProfile> getAllStoryProfiles() {
		return storyHistService.getAllStoryProfiles();
	}
	
	@PostMapping(path="/getStoryProfilesByIds", consumes = "application/json", produces = "application/json")
	public List<StoryProfile> getStoryProfilesByIds(@RequestBody List<Integer> storyIds) {
		return storyHistService.getStoryProfilesByIds(storyIds);
	}
	
	@GetMapping(path="/getStoryProfilesByBoardId/{boardId}")
	public List<StoryProfile> getStoryProfilesByBoardId(@PathVariable int boardId) {
		return storyHistService.getStoryProfilesByBoardId(boardId);
	}
	
	@GetMapping(path="/deleteStoryProfilesByBoardId/{boardId}")
	public void deleteStoryProfilesByBoardId(@PathVariable int boardId) {
		storyHistService.deleteStoryProfilesByBoardId(boardId);

	}
	
	@GetMapping(path="/deleteStoryProfileAndEvents/{storyId}")
	public void deleteStoryProfileAndEvents(@PathVariable int storyId) {
		storyHistService.deleteStoryProfileAndEvents(storyId);
	}
}
