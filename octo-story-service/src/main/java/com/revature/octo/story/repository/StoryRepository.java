package com.revature.octo.story.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.octo.story.model.Story;

@Repository
public interface StoryRepository extends CrudRepository<Story, Integer> {
	Story findById(int id);
	Story findByName(String name);
}
