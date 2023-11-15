package org.itnow.cursoSpring.clasecuatro.repository;

import java.util.List;

import org.itnow.cursoSpring.clasecuatro.models.*;

public interface AutomovilRepository {
	
	void insert(Automovil auto);
	void update(Automovil auto);
	void delete(int id);
	List<Automovil> findAll();
	Automovil findById(int id);
}
