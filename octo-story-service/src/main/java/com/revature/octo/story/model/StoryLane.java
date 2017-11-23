package com.revature.octo.story.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="STORY_LANE")
/**
 * Represents the lane a story could potentially be located.
 * 
 * Potentially should move this to a separate micro-service.
 * Especially since it's not tied via JPA to the stories themselves.
 * 
 * Do this if there's time.
 * 
 * @author jpwru
 *
 */
public class StoryLane {
	@Id
	@Min(0)
	private int id;
	
	@NotNull
	private String name;
	
	public StoryLane() {
	}
	
	public StoryLane(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

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
	
	@Override
	public String toString() {
		return "SbLane [id=" + id + ", name=" + name + "]";
	}
}
