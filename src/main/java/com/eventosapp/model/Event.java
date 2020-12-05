package com.eventosapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eventId;
	//@NotEmpty
	private String name;
	//@NotEmpty
	private String local;
	//@NotEmpty
	private String date;
	//@NotEmpty
	private LocalTime schedule;
	
	@OneToMany
	private List<Guest> guests;

	public Event() { }


	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public LocalTime getSchedule() {
		return schedule;
	}

	public void setSchedule(LocalTime schedule) {
		this.schedule = schedule;
	}
}
