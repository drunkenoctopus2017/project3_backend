package com.revature.octo.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.revature.octo.task.model.OctoTask;
import com.revature.octo.task.repository.OctoTaskRepository;

@RestController
public class OctoTaskController {
	/*
	 * 	
	 * public ScrumBoardTask createNewScrumBoardTask(ScrumBoardTask task);
		public ScrumBoardTask getScrumBoardTaskById(int id);
		public ScrumBoardTask updateScrumBoardTask(ScrumBoardTask task);
	 */
	
	@Autowired
	OctoTaskRepository taskRepo;
	
	public OctoTask createOrUpdateTask(OctoTask task) {
		taskRepo.save(task);
		return task;
	}
	
	public OctoTask getTaskById(int id) {
		OctoTask task = taskRepo.findOne(id);
		return task;
	}
	
	
}
