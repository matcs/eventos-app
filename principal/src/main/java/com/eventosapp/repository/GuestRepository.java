package com.eventosapp.repository;

import com.eventosapp.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventosapp.model.Event;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
	Iterable<Guest> findByEvent(Event event);
	Guest findByGuestId(int id);
}
