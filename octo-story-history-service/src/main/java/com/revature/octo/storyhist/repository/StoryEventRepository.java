package com.revature.octo.storyhist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.octo.storyhist.model.StoryEvent;

@Repository
public interface StoryEventRepository extends CrudRepository<StoryEvent, Integer>{

}
