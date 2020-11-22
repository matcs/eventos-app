package com.eventosapp.model;

import java.time.LocalDate;
import java.time.LocalTime;
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
	@NotEmpty
	private String name;
	@NotEmpty
	private String local;
	@NotEmpty
	private LocalDate date;
	@NotEmpty
	private LocalTime schedule;
	
	@OneToMany
	private List<Guest> guests;

	public Event() { }

	public Event(int eventId, @NotEmpty String name, @NotEmpty String local, @NotEmpty LocalDate date, @NotEmpty LocalTime schedule) {
		this.eventId = eventId;
		this.name = name;
		this.local = local;
		this.date = date;
		this.schedule = schedule;
	}

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getSchedule() {
		return schedule;
	}

	public void setSchedule(LocalTime schedule) {
		this.schedule = schedule;
	}
}
