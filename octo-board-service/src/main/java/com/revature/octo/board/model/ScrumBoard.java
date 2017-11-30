package com.revature.octo.board.model;

import java.io.IOException;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//import com.revature.octo.user.model.JSONDateAdapter;

@Entity
@Table(name="SCRUM_BOARD")
public class ScrumBoard {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Size(min=2)
	@NotNull
	private String name;
	
	@JsonSerialize(using=DateSerializer.class)
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@NotNull
	@Min(1)
	private int duration;
	
	public ScrumBoard() {}
	
	public ScrumBoard(String name, Date startDate, int duration) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.duration = duration;
	}
	
	public ScrumBoard(int id, String name, Date startDate, int duration) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.duration = duration;
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
		return "ScrumBoard [id=" + id + ", name=" + name + ", startDate=" + startDate + ", duration=" + duration + "]";
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