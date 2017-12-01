package com.revature.octo.storyhist.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="STORY_PROFILE")
public class StoryProfile {
	
	@Id
	private int id;
	
	@NotNull
	private int boardId;
	
	@Min(0)
	private int points;
	
	@OneToMany(mappedBy="storyProfile", fetch=FetchType.EAGER) 
	private List<StoryEvent> storyEvents = new ArrayList<>();
	
	public StoryProfile() {}

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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void setPoints(String points) {
		this.points = Integer.parseInt(points);
	}
	
	public List<StoryEvent> getStoryEvents() {
		return storyEvents;
	}

	public void setStoryEvents(List<StoryEvent> storyEvents) {
		this.storyEvents = storyEvents;
	}

	@Override
	public String toString() {
		return "StoryProfile [id=" + id + ", boardId=" + boardId + ", points=" + points + ", storyEvents=" + storyEvents
				+ "]";
	}
}
