package com.revature.octo.task.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.task.model.Task;
import com.revature.octo.task.repository.TaskRepository;
import com.revature.octo.task.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	TaskRepository taskRepo;
	
	@Autowired
	TaskService taskService;
	
	public List<Task> getScrumTasksByIds(List<Integer> taskIds){
		return taskService.getScrumTasksByIds(taskIds);
	}
	
	@GetMapping(path="/getTaskById/{id}")
	public Task getTaskById(@PathVariable int id) {
		return taskService.getTaskById(id);
	}
	
	@GetMapping(path="/getAllTasks")
	public List<Task> getAllTasks(){
		return taskService.getAllTasks();
	}
	
	@GetMapping(path="/getTaskByStoryId/{storyId}")
	public List<Task> getTaskByStoryId(@PathVariable int storyId){
		return taskService.getTaskByStoryId(storyId);
	}
	
	@PostMapping(path="/createUpdateTask",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Task createUpdateTask(@RequestBody Task t) {
		return taskService.createUpdateTask(t);
	}
	
//	@GetMapping("/createTestTasks")
//	public List<Task> createTestTask(){
//		return taskService.createTestTask();
//	}
		
	@GetMapping(path="/deleteTaskById/{id}")
	public String deleteTaskById(@PathVariable int id) {
		return taskService.deleteTaskById(id);
	}

	@GetMapping("/deleteTasksByStoryId/{storyId}")
	public String deleteTasksByStoryId(@PathVariable int storyId){
		return taskService.deleteTasksByStoryId(storyId);
	}
}
