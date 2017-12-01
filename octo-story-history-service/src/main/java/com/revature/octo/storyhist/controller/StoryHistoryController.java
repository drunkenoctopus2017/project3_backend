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
	 * When a story is either created or updated, persist it in the history.
	 * This is called by StoryController in the Story management micro-service.
	 * 
	 * @param message
	 */
	@StreamListener(target=Sink.INPUT)
	public void updateStoryHistory(Map<String, Object> message) {
		System.out.println("StoryHistoryController updateStoryHistory: " + message.toString());
		Integer storyId = (Integer) message.get("storyId");
		Integer boardId = (Integer) message.get("boardId");
		Integer points = (Integer) message.get("points");
		Date updated = (Date) message.get("updated");
		Boolean done = (Boolean) message.get("done");
		
		StoryProfile storyProfile = profileRepo.findById(storyId);
		if (storyProfile == null) {
			//Story profile not found. Create a new one.
			storyProfile = new StoryProfile();
		}
		storyProfile.setId(storyId);
		storyProfile.setBoardId(boardId);
		storyProfile.setPoints(points);
		storyProfile = profileRepo.save(storyProfile);
		
		final int ONE_DAY = 86400000;
		long updatedDay = (long) Math.floor(updated.getTime() / ONE_DAY);
		StoryEvent storyEvent = null;
		for (StoryEvent savedStoryEvent : storyProfile.getStoryEvents()) {
			long lastUpdatedDay = (long) Math.floor(savedStoryEvent.getModifiedDate().getTime() / ONE_DAY);
			if (updatedDay == lastUpdatedDay) {
				storyEvent = savedStoryEvent;
				break;
			}
		}
		if (storyEvent == null) {
			storyEvent = new StoryEvent();
		} else {
			storyEvent = eventRepo.findOne(storyEvent.getId());
		}
		//StoryEvent storyEvent = eventRepo.findByStoryProfileAndModifiedDate(storyProfile, updated);
		//if (storyEvent == null) {
		//	storyEvent = new StoryEvent();
		//}
		storyEvent.setStoryProfile(storyProfile);
		storyEvent.setDone(done ? 1 : 0);
		storyEvent.setModifiedDate(updated);
		eventRepo.save(storyEvent);
		/*
		Integer storyId = (Integer) message.get("id");
		StoryProfile sp;
		if (storyId != null) {
			sp = profileRepo.findById(storyId);
			if (sp == null) {
				sp = new StoryProfile();
			}
		} else {
			sp = new StoryProfile();
		}
		
		sp.setId(storyId);
		sp.setBoardId((Integer) message.get("boardId"));
		sp.setPoints((Integer) message.get("points"));
		System.out.println("Story ID: " + storyId);
		sp = profileRepo.save(sp);
		Date updated = (Date) message.get("updated");
		
		//System.out.println("Found StoryProfile: " + sp);
		StoryEvent storyEvent = eventRepo.findByStoryProfileAndModifiedDate(sp, updated);
		if (storyEvent == null) {
			storyEvent = new StoryEvent();
			storyEvent.setModifiedDate(updated);
		}
		storyEvent.setStoryProfile(sp);
		storyEvent.setDone(
			(Integer) message.get("laneId") == 60 ? 1 : 0
		);
		eventRepo.save(storyEvent);
		*/
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
	
	@GetMapping(path="/deleteStoryProfilesByBoardId/{boardId}")
	public void deleteStoryProfilesByBoardId(@PathVariable int boardId) {
		List<StoryProfile> toBeDeleted = profileRepo.findByBoardId(boardId);
		for(StoryProfile sp : toBeDeleted) {
			profileRepo.delete(sp);
		}
	}
}
