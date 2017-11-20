package com.revature.octo.board.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="SCRUM_BOARDS")
@Component
public class ScrumBoard {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
		

	private String name;
	
	private Date startDate;
	
	private int duration;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "OctoBoard [id=" + id + ",  name=" + name + ", startDate=" + startDate + ", duration="
				+ duration + "]";
	}
	
	

}
