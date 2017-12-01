package com.revature.octo.story.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.bouncycastle.asn1.dvcs.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.story.message.StoryUpdateMessageSource;
import com.revature.octo.story.model.Story;
import com.revature.octo.story.repository.StoryRepository;

//@EnableBinding(Sink.class)
@RestController
public class StoryController {
	
	@Autowired
	StoryUpdateMessageSource source;
	
	@Autowired
	StoryRepository storyRepo;
	
	/**
	 * This can only be called when a story is UPDATED.
	 * The story must pre-exist in the separate story database.
	 * A separate method must be used when a new story is created.
	 * 
	 * Note: this method overloads the endpoint updateStory method.
	 * 
	 * @param message The key/values storing data to update the story with.
	 */
	/*
	@StreamListener(target=Sink.INPUT)
	public void updateStory(Map message) {
		System.out.println("update story with params: " + message.toString());
		Integer storyId = (Integer) message.get("id");
		Story sp;
		if (storyId == null || !storyRepo.exists(storyId)) {
			//System.out.println("***updateStory story missing for storyId: " + storyId);
			//return;
			sp = new Story();
			sp.setBoardId((Integer) message.get("boardId"));
		} else {
			sp = storyRepo.findOne(storyId);
		}
		sp.setPoints((Integer) message.get("points"));
		sp.setDescription((String) message.get("description"));
		sp.setLaneId((Integer) message.get("laneId"));
		sp.setName((String) message.get("name"));
		
		storyRepo.save(sp);
		System.out.println("story was updated " + sp.toString());
	}
	*/
	
	@GetMapping(path="/getStoriesByBoardId/{boardId}")
	public List<Story> getStoriesByBoardId(@PathVariable int boardId) {
		return storyRepo.findByBoardId(boardId);
	}
	
	@GetMapping(path="/getStories")
	public List<Story> getStories() {
		return (List<Story>) storyRepo.findAll();
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
	
	@GetMapping(path="/deleteStoriesByBoardId/{boardId}")
	public String deleteStoriesByBoardId(@PathVariable int boardId) {
		List<Story> toBeDeleted = storyRepo.findByBoardId(boardId);
		for(Story s : toBeDeleted) {
			storyRepo.delete(s);
		}
		//we don't currently delete stories, but when we do, send a message here as well.
		return "successfully deleted stories for this board: "+boardId;
	}
}
