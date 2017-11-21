package com.revature.octo.task.model;

import javax.persistence.Id;

public class OctoTask {
	
	@Id
	private int id;
	
	private int status;
	
	private String description;
	
	private int storyId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getStoryId() {
		return storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}

	@Override
	public String toString() {
		return "OctoTask [id=" + id + ", status=" + status + ", description=" + description + ", storyId=" + storyId
				+ "]";
	}
	
	
}
