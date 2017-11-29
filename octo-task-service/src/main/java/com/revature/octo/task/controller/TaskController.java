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

@RestController
public class TaskController {

	@Autowired
	TaskRepository taskRepo;
	
	public List<Task> getScrumTasksByIds(List<Integer> taskIds){
		return taskRepo.findByIdIn(taskIds);
	}
	
	@GetMapping(path="/getTaskById/{id}")
	public Task getTaskById(@PathVariable int id) {
		Task task = taskRepo.findOne(id);
		return task;
	}
	
	@GetMapping(path="/getAllTasks")
	public List<Task> getAllTasks(){
		return (List<Task>) taskRepo.findAll();
	}
	
	@GetMapping(path="/getTaskByStoryId/{storyId}")
	public List<Task> getTaskByStoryId(@PathVariable int storyId){
		return taskRepo.getTaskByStoryId(storyId);
	}
	
	@PostMapping(path="/createUpdateTask",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Task createUpdateTask(@RequestBody Task t) {
		return taskRepo.save(t);
	}
	
	@GetMapping("/createTestTasks")
	public List<Task> createTestTask(){
		ArrayList<Task> taskList = new ArrayList<>();
		Task task = taskRepo.findOne(1);
		if(task == null) {
			task = new Task( 1, 1, 1, "test");
			taskRepo.save(task);
			System.out.println("creating: " + task);
		} else {
			System.out.println("*task found: " + task);
		}
		
		taskList.add(task);
		
		return taskList;
	}
	
	
	@GetMapping(path="/deleteTaskById/{id}")
	public String deleteTaskById(@PathVariable int id) {
		System.out.println("Deleting task " + id);
		taskRepo.delete(id);
		System.out.println("Task " + id + " has been deleted");
		return "Successfully deleted task" + id;
	}
}
