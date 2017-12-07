package com.revature.octo.storyhist.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.octo.storyhist.model.StoryEvent;
import com.revature.octo.storyhist.model.StoryProfile;

@Repository
public interface StoryEventRepository extends CrudRepository<StoryEvent, Integer> {
	public StoryEvent findByStoryProfileAndModifiedDate(StoryProfile storyProfile, Date modifiedDate);

	public List<StoryEvent> findByStoryProfile(StoryProfile storyProfile);
}