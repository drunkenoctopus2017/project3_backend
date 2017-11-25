package com.revature.octo.task.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.octo.task.model.ScrumTask;

@Repository
public interface ScrumTaskRepository extends CrudRepository<ScrumTask, Integer>{
	
	List<ScrumTask> findByIdIn(List<Integer> taskList);
}
