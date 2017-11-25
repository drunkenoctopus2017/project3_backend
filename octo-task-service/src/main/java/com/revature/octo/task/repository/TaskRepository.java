package com.revature.octo.task.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.octo.task.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer>{
	
	List<Task> findByIdIn(List<Integer> taskList);
	List<Task> getTaskByStoryId(int storyId);
}
