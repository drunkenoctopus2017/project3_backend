package com.revature.octo.task.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TASK")
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	private int storyId;
	
	@NotNull
	private int status;

	@NotNull
	private String description;
		
	public Task() {
	}
	
	public Task(int id, int storyId, int status, String description) {
		this.id= id;
		this.storyId = storyId;
		this.status = status;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getStoryId() {
		return storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
