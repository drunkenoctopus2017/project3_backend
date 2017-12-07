package com.revature.octo.story.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.story.model.StoryLane;
import com.revature.octo.story.service.StoryLaneService;

@RestController
public class StoryLaneController {
	
	@Autowired 
	StoryLaneService storyLaneService;
	
	@GetMapping(path="/getStoryLanes")
	public List<StoryLane> getStoryLanes() {
		return storyLaneService.getStoryLanes();
	}
	
	//TODO implement authorization so that these methods can only be called by Administrators (ie We the programmers)
	@GetMapping(path="/createStoryLanes")
	public List<StoryLane> createStoryLanes() {
		return storyLaneService.createStoryLanes();
	}
}
