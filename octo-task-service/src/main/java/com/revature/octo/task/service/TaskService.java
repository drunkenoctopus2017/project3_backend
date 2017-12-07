package com.revature.octo.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.octo.task.model.Task;
import com.revature.octo.task.repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	TaskRepository taskRepo;
	
	public List<Task> getScrumTasksByIds(List<Integer> taskIds){
		return taskRepo.findByIdIn(taskIds);
	}
	
	public Task getTaskById(int id) {
		Task task = taskRepo.findOne(id);
		return task;
	}
	
	public List<Task> getAllTasks(){
		return (List<Task>) taskRepo.findAll();
	}
	
	public List<Task> getTaskByStoryId(int storyId){
		return taskRepo.getTaskByStoryId(storyId);
	}
	
	public Task createUpdateTask(Task t) {
		return taskRepo.save(t);
	}
	
//	public List<Task> createTestTask(){
//		ArrayList<Task> taskList = new ArrayList<>();
//		Task task = taskRepo.findOne(1);
//		if(task == null) {
//			task = new Task( 1, 1, 1, "test");
//			taskRepo.save(task);
//			System.out.println("creating: " + task);
//		} else {
//			System.out.println("*task found: " + task);
//		}
//		
//		taskList.add(task);
//		
//		return taskList;
//	}
	
	public String deleteTaskById(int id) {
		System.out.println("Deleting task " + id);
		taskRepo.delete(id);
		System.out.println("Task " + id + " has been deleted");
		return "Successfully deleted task" + id;
	}
	
	public String deleteTasksByStoryId(int storyId){
		List<Task> toBeDeleted = taskRepo.getTaskByStoryId(storyId);
		for(Task t : toBeDeleted) {
			taskRepo.delete(t);
		}
		return "Sucessfully deleted tasks for this story: "+storyId;
	}
}
