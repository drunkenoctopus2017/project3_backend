package com.revature.octo.task;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.revature.octo.task.model.Task;
import com.revature.octo.task.repository.TaskRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OctoTaskServiceApplicationTests {
	
	@Autowired
	TaskRepository taskRepo;

	@BeforeClass
	public static void staticPrep() {
		
	}
	
	@Before
	public void methodPrep() {
		
	}
	
	@Test
	public void testTaskRepoFindOne() {
		Task task = taskRepo.findOne(1);
	}
	
	@Test
	public void testTaskRepoFindByIdIn() {
		List<Integer> taskIds = null;
		List<Task> tasks = taskRepo.findByIdIn(taskIds); 
	}

	@Test
	public void testTaskRepoFindAll() {
		Task task = taskRepo.findOne(1);
		List<Task> tasks = (List<Task>) taskRepo.findAll();
	}

	@Test
	public void testTaskRepoGetTaskByStoryId() {
		Task task = taskRepo.findOne(1);
		taskRepo.getTaskByStoryId(2);
	}
	
	@Test
	public void testTaskRepoSave() {
		Task task = taskRepo.findOne(1);
		taskRepo.save(task);
	}

	@Test
	public void testTaskRepoDelete() {
		Task task = taskRepo.findOne(1);
		taskRepo.delete(task);
	}

}
