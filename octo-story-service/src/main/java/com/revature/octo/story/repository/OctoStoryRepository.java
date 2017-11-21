package com.revature.octo.story.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.octo.story.model.OctoStory;

@Repository
public interface OctoStoryRepository extends CrudRepository<OctoStory, Integer> {

}
