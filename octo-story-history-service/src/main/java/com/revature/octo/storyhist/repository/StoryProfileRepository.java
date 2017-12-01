package com.revature.octo.storyhist.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.octo.storyhist.model.StoryProfile;

public interface StoryProfileRepository extends CrudRepository<StoryProfile, Integer> {
	List<StoryProfile> findByIdIn(List<Integer> storyIds);
	List<StoryProfile> findByBoardId(int boardId);
	StoryProfile findById(int id);
}
