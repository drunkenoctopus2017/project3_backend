package com.revature.octo.story.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="SCRUM_BOARD_STORIES")
@Component
public class ScrumBoardStory {
	
	@Id
	@GeneratedValue
	private int id;
	
	private int scrumBoardId;
	
	private List<Integer> tasksId;
	
	private String description;
	
	private int points;
	
	private int laneId;
	
	private Date finishTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScrumBoardId() {
		return scrumBoardId;
	}

	public void setScrumBoardId(int scrumBoardId) {
		this.scrumBoardId = scrumBoardId;
	}

	public List<Integer> getTasksId() {
		return tasksId;
	}

	public void setTasksId(List<Integer> tasksId) {
		this.tasksId = tasksId;
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

	@Override
	public String toString() {
		return "ScrumBoardStory [id=" + id + ", scrumBoardId=" + scrumBoardId + ", tasksId=" + tasksId
				+ ", description=" + description + ", points=" + points + ", laneId=" + laneId + ", finishTime="
				+ finishTime + "]";
	}
	
	
}
