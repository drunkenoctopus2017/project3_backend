package com.revature.octo.storyhist.controller;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.revature.octo.storyhist.model.StoryEvent;
import com.revature.octo.storyhist.model.StoryProfile;
import com.revature.octo.storyhist.repository.StoryEventRepository;
import com.revature.octo.storyhist.repository.StoryProfileRepository;

@EnableBinding(Sink.class)
@RestController
public class StoryHistoryController {
	
	@Autowired
	StoryProfileRepository profileRepo;
	
	@Autowired
	StoryEventRepository eventRepo;
	
	@StreamListener(target=Sink.INPUT)
	public void test(Map message) {
		System.out.println("Test Message: " + message);
		Integer id = (Integer)message.get("id");
		System.out.println("ID: " + id);
		StoryProfile sp = profileRepo.findOne(id);
		if(sp != null) {
			System.out.println("Found StoryProfile: " + sp);
		}else {
			System.out.println("DIDN'T FIND... create new?");
		}
	}
	
	@GetMapping("/")
	public String chartTest() {
		return "Hello Chart";
	}
	
	@GetMapping("/getAllStoryProfiles") 
	public List<StoryProfile> getAllStoryProfiles() {
		return (List<StoryProfile>) profileRepo.findAll();
	}
	
	@PostMapping(path="/getStoryProfilesByIds", consumes = "application/json", produces = "application/json")
	public List<StoryProfile> getStoryProfilesByIds(@RequestBody List<Integer> storyIds) {
		return profileRepo.findByIdIn(storyIds);
	}
	
	@GetMapping(path="/getStoryProfilesByBoardId/{boardId}")
	public List<StoryProfile> getStoryProfilesByBoardId(@PathVariable int boardId) {
		return profileRepo.findByBoardId(boardId);
	}
	/*
	@GetMapping("/createHistory")
	public List<StoryProfile> createHistory() {
		List<StoryProfile> newStories = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			StoryProfile newStory;
			newStory = new StoryProfile();
			newStory.setBoardId(1);
			newStory.setPoints(i);
			newStories.add(newStory);
		}
		newStories = (List<StoryProfile>) profileRepo.save(newStories);
		
		Calendar cal = Calendar.getInstance();
		List<StoryEvent> newEvents = new ArrayList<>();
		for (StoryProfile storyProfile : newStories) {
			StoryEvent newEvent = new StoryEvent();
			newEvent.setStoryProfile(storyProfile);
			newEvent.setDone(0);
			cal.set(2017, 10, 1);
			newEvent.setModifiedDate(cal.getTime());
			newEvents.add(newEvent);
			
			newEvent = new StoryEvent();
			newEvent.setStoryProfile(storyProfile);
			newEvent.setDone(1);
			cal.set(2017, 10, 1 + storyProfile.getPoints());
			newEvent.setModifiedDate(cal.getTime());
			newEvents.add(newEvent);
		}
		eventRepo.save(newEvents);
		
		return newStories;
	}
	*/
}
