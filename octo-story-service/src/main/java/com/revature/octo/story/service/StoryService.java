package com.revature.octo.story.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.revature.octo.story.message.StoryUpdateMessageSource;
import com.revature.octo.story.model.Story;
import com.revature.octo.story.repository.StoryRepository;

@Service
public class StoryService {

	@Autowired
	StoryUpdateMessageSource source;
	
	@Autowired
	StoryRepository storyRepo;
	
	public List<Story> getStoriesByBoardId(int boardId){
		return storyRepo.findByBoardId(boardId);
	}
	
	public List<Story> getStories(){
		return (List<Story>) storyRepo.findAll();
	}
	
	public Story updateStory(Story story) {
		HashMap<String, Object> params = new HashMap<>();
		story = storyRepo.save(story);
		
		params.put("storyId", story.getId());
		params.put("boardId", story.getBoardId());
		params.put("points", story.getPoints());
		params.put("updated", new Date());
		params.put("done", story.getLaneId() == 60);
		source.updateStoryMessagePlace().send(
			MessageBuilder.withPayload(params).setHeader("method", "update").build()
		);
		return story;
	}
	
	public String deleteStoriesByBoardId(int boardId) {
		List<Story> toBeDeleted = storyRepo.findByBoardId(boardId);
		for(Story s : toBeDeleted) {
			storyRepo.delete(s);
		}
		//we don't currently delete stories, but when we do, send a message here as well.
		return "successfully deleted stories for this board: "+boardId;
	}
}
