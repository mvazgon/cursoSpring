package org.itnow.cursoSpring.clasecuatro.repository;

import org.itnow.cursoSpring.clasecuatro.models.Automovil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Esta clase extiende un objeto JpaRespository, q le pasamos como argumentos un objeto Automovil o un Integer
 */
@Repository
public interface AutomovilJPARepository extends JpaRepository<Automovil, Integer>{

}
