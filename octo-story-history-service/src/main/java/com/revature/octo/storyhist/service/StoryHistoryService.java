package com.revature.octo.storyhist.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.octo.storyhist.model.StoryEvent;
import com.revature.octo.storyhist.model.StoryProfile;
import com.revature.octo.storyhist.repository.StoryEventRepository;
import com.revature.octo.storyhist.repository.StoryProfileRepository;

@Service
public class StoryHistoryService {
	@Autowired
	StoryProfileRepository profileRepo;
	
	@Autowired
	StoryEventRepository eventRepo;
	
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
		
		storyEvent.setStoryProfile(storyProfile);
		storyEvent.setDone(done ? 1 : 0);
		storyEvent.setModifiedDate(updated);
		eventRepo.save(storyEvent);
	}
	
	public List<StoryProfile> getAllStoryProfiles() {
		return (List<StoryProfile>) profileRepo.findAll();
	}
	
	public List<StoryProfile> getStoryProfilesByIds( List<Integer> storyIds) {
		return profileRepo.findByIdIn(storyIds);
	}
	
	public List<StoryProfile> getStoryProfilesByBoardId(int boardId) {
		return profileRepo.findByBoardId(boardId);
	}
	
	public void deleteStoryProfilesByBoardId(int boardId) {
		List<StoryProfile> toBeDeleted = profileRepo.findByBoardId(boardId);
		for(StoryProfile sp : toBeDeleted) {
			List<StoryEvent> ses = eventRepo.findByStoryProfile(sp);
			for(StoryEvent se : ses) {
				eventRepo.delete(se);
			}
			profileRepo.delete(sp);
		}
	}
	
	public void deleteStoryProfileAndEvents(int storyId) {
		StoryProfile sp = profileRepo.findById(storyId);
		List<StoryEvent> ses = eventRepo.findByStoryProfile(sp);
		for(StoryEvent se : ses) {
			eventRepo.delete(se);
		}
		profileRepo.delete(sp);
	}
}
