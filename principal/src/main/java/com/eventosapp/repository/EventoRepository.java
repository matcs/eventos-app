package com.eventosapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventosapp.model.Evento;

@Repository
public interface EventoRepository extends CrudRepository<Evento,String>{
	Evento findByCodigo(long codigo);
}
