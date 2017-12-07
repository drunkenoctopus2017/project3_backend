package com.revature.octo.story.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.revature.octo.story.model.StoryLane;
import com.revature.octo.story.repository.StoryLaneRepository;

@Service
public class StoryLaneService {
	@Autowired
	StoryLaneRepository storyLaneRepo;
	
	public List<StoryLane> getStoryLanes() {
		return (List<StoryLane>) storyLaneRepo.findAll();
	}
	
	public List<StoryLane> createStoryLanes() {
		List<StoryLane> returnList = new ArrayList<>();
		returnList.add(
			storyLaneRepo.exists(10) ? storyLaneRepo.findOne(10) : storyLaneRepo.save(new StoryLane(10, "Backlog")) 
		);
		returnList.add(
			storyLaneRepo.exists(20) ? storyLaneRepo.findOne(20) : storyLaneRepo.save(new StoryLane(20, "To Do"))
		);
		returnList.add(
			storyLaneRepo.exists(30) ? storyLaneRepo.findOne(30) : storyLaneRepo.save(new StoryLane(30, "In Progress"))
		);
		returnList.add(
			storyLaneRepo.exists(40) ? storyLaneRepo.findOne(40) : storyLaneRepo.save(new StoryLane(40, "Test"))
		);
		returnList.add(
			storyLaneRepo.exists(50) ? storyLaneRepo.findOne(50) : storyLaneRepo.save(new StoryLane(50, "Verify"))
		);
		returnList.add(
			storyLaneRepo.exists(60) ? storyLaneRepo.findOne(60) : storyLaneRepo.save(new StoryLane(60, "Done"))
		);
		return returnList;
	}
}
