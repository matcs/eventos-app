package com.eventosapp.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Guest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int guestId;
	@NotEmpty
	private String cpf;
	@NotEmpty
	private String name;
	
	@ManyToOne
	private Event event;

	public int getGuestId() {
		return guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
