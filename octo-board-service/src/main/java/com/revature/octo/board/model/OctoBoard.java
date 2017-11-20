package com.revature.octo.board.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;


public class OctoBoard {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOARD_ID_SEQ")
	private int id;
	
	@JoinTable(name="USER_BOARDS")
	private int userId;
	
	private int storyId;

	private String name;
	
	private Date startDate;
	
	private int duration;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getStoryId() {
		return storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	

	@Override
	public String toString() {
		return "OctoBoard [id=" + id + ", userId=" + userId + ", name=" + name + ", startDate=" + startDate + ", duration="
				+ duration + "]";
	}
	
	

}
