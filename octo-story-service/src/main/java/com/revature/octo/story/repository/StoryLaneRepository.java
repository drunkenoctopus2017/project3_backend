package com.revature.octo.story.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.octo.story.model.StoryLane;

@Repository
public interface StoryLaneRepository extends CrudRepository<StoryLane, Integer> {

}
