package com.revature.octo.task.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.task.model.ScrumTask;
import com.revature.octo.task.repository.ScrumTaskRepository;

@RestController
public class ScrumTaskController {

	@Autowired
	ScrumTaskRepository taskRepo;
	
	public List<ScrumTask> getScrumTasksByIds(List<Integer> taskIds){
		return taskRepo.findByIdIn(taskIds);
	}
	
	@GetMapping(path="/getTaskById/{id}")
	public ScrumTask getTaskById(@PathVariable int id) {
		ScrumTask task = taskRepo.findOne(id);
		return task;
	}
	
	@GetMapping("/getAllTasks")
	public List<ScrumTask> getAllTasks(){
		return (List<ScrumTask>) taskRepo.findAll();
	}
	
	@GetMapping("/createTestTasks")
	public List<ScrumTask> createTestTask(){
		ArrayList<ScrumTask> taskList = new ArrayList<>();
		ScrumTask task = taskRepo.findOne(1);
		if(task == null) {
			task = new ScrumTask( 1, 1, "test");
			taskRepo.save(task);
			System.out.println("creating: " + task);
		} else {
			System.out.println("*task found: " + task);
		}
		
		taskList.add(task);
		
		return taskList;
	}
}
