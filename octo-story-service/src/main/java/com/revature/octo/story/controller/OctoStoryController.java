package com.revature.octo.story.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.octo.story.model.OctoStory;
import com.revature.octo.story.repository.OctoStoryRepository;

public class OctoStoryController {
	/*
	 * Story DAO Methods
	 * 	public ScrumBoardStory createNewStory(ScrumBoardStory s);
		public ScrumBoardStory getScrumBoardStoryById(int id);	
		public ScrumBoardStory updateScrumBoardStory(ScrumBoardStory story);
	 */
	
	@Autowired
	OctoStoryRepository storyRepo;
	
	public OctoStory createOrUpdateStory(OctoStory story) {
		storyRepo.save(story);
		return story;
	}
	
	public OctoStory getStory(int id) {
		OctoStory story = storyRepo.findOne(id);
		return story;
	}
}
