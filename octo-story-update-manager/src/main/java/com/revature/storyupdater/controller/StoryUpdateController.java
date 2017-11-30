package com.revature.storyupdater.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.storyupdater.message.StoryUpdateMessageSource;

@RestController
public class StoryUpdateController {

	@Autowired
	StoryUpdateMessageSource source;

	@PostMapping(path="/updateStory", consumes=MediaType.APPLICATION_JSON_VALUE/*, produces=MediaType.APPLICATION_JSON_VALUE*/)
	public String updateStory(@RequestBody Map story) {
		story.put("updated", new Date());
		source.updateStoryMessagePlace().send(
			MessageBuilder.withPayload(story).build()
		);
		return "success";
	}
}
