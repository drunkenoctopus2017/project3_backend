package com.revature.octo.story.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="STORY")
public class Story {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	private int boardId;
	
	@NotNull
	private int laneId;
	
	@NotNull
	private String name;
	
	@Min(0)
	private int points;
	
	@Size(max=2000)
	private String description;
	
	public Story() {}
	
	public Story(int boardId, int laneId, String name, int points, String description) {
		super();
		this.boardId = boardId;
		this.laneId = laneId;
		this.name = name;
		this.points = points;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getLaneId() {
		return laneId;
	}

	public void setLaneId(int laneId) {
		this.laneId = laneId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void setPoints(String points) {
		this.points = Integer.parseInt(points);
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Story [id=" + id + ", boardId=" + boardId + ", laneId=" + laneId + ", name=" + name + ", points="
				+ points + ", description=" + description + "]";
	}
}
