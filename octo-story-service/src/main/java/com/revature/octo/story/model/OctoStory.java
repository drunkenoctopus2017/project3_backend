package com.revature.octo.story.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

public class OctoStory {
	@Id
	private int id;
	
	private String description;
	
	private int points;
	
	private int laneId;
	
	private Date finishTime;
	
	private int boardId;
	
	private List<Integer> tasksId = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLaneId() {
		return laneId;
	}

	public void setLaneId(int laneId) {
		this.laneId = laneId;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public List<Integer> getTasksId() {
		return tasksId;
	}

	public void setTasksId(List<Integer> tasksId) {
		this.tasksId = tasksId;
	}

	@Override
	public String toString() {
		return "Story [id=" + id + ", description=" + description + ", points=" + points + ", laneId=" + laneId
				+ ", finishTime=" + finishTime + ", boardId=" + boardId + ", tasksId=" + tasksId + "]";
	}
	
}
