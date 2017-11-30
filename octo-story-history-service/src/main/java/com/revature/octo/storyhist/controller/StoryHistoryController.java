package com.revature.octo.storyhist.controller;

import java.util.Date;
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
	
	/**
	 * This can only be called when a story is UPDATED.
	 * The story must pre-exist in the separate story database.
	 * A separate method must be used when a new story is created.
	 * 
	 * @param message
	 */
	@StreamListener(target=Sink.INPUT)
	public void updateStoryHistory(Map message) {
		System.out.println("Test Message: " + message);
		Integer storyId = (Integer) message.get("id");
		if (!profileRepo.exists(storyId)) {
			System.out.println("Story profile missing for storyId: " + storyId);
			return;
		}
		System.out.println("ID: " + storyId);
		StoryProfile sp = profileRepo.findOne(storyId);
		sp.setPoints((Integer) message.get("points"));
		profileRepo.save(sp);
		Date updated = (Date) message.get("updated");
		
		//System.out.println("Found StoryProfile: " + sp);
		StoryEvent storyEvent = eventRepo.findByStoryProfileAndModifiedDate(sp, updated);
		if (storyEvent != null) {
			storyEvent = new StoryEvent();
			storyEvent.setStoryProfile(sp);
			storyEvent.setModifiedDate(updated);
		}
		storyEvent.setDone(
			(Integer) message.get("laneId") == 60 ? 1 : 0
		);
		eventRepo.save(storyEvent);
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