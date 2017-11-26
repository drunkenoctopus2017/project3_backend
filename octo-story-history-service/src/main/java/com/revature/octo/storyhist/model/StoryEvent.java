package com.revature.octo.storyhist.model;

import java.io.IOException;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


@Entity
@Table(name="STORY_EVENT")
public class StoryEvent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="STORY_ID")
	private StoryProfile storyProfile;
	
	//Use 1 and 0 for true/false. 
	//This will be useful for the arithmetic function later.
	//Using a boolean would require parsing logic. KISS.
	@Min(0)
	@Max(1)
	private byte done;
	
	@JsonSerialize(using=DateSerializer.class)
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	
	public StoryEvent() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StoryProfile getStoryProfile() {
		return storyProfile;
	}

	public void setStoryProfile(StoryProfile storyProfile) {
		this.storyProfile = storyProfile;
	}

	public byte getDone() {
		return done;
	}
	
	public void setDone(Integer done) {
		this.done = done.byteValue();
	}
	
	public void setDone(byte done) {
		this.done = done;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "StoryEvent [id=" + id + ", storyProfile=" + storyProfile.getId() + ", done=" + done + ", modifiedDate="
				+ modifiedDate + "]";
	}
}

class DateSerializer extends StdSerializer<Date> {
	
	/**
	 * Version 1.0
	 */
	private static final long serialVersionUID = 1L;
	
	public DateSerializer() {
		this(null);
	}
	
	public DateSerializer(Class<Date> t) {
		super(t);
	}
	
	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		//System.out.println("serialize time: " + value.toString());
		jgen.writeNumber(value.getTime());
	}
}