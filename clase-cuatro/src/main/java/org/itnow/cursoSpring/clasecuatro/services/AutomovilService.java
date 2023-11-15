package org.itnow.cursoSpring.clasecuatro.services;

import java.util.*;
import org.apache.juli.logging.*;
import org.itnow.cursoSpring.clasecuatro.models.Automovil;
import org.itnow.cursoSpring.clasecuatro.repository.AutomovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutomovilService implements CrudService<Automovil> {
	
		Log log = LogFactory.getLog(Automovil.class);
		
		@Autowired
		AutomovilRepository repo ;
	
		@Override
		public void registrarNuevo(Automovil nuevo) throws CrudException {
			log.info("Registrando un Automovil nuevo");
			if ((nuevo.getModelo()==null) || (nuevo.getModelo().trim().length()==0)) {
				throw new CrudException("Validación: el modelo del automovil se requiere");
			}
			this.repo.insert(nuevo);
		}
		
		@Override
		public void actualizarInfo(Automovil existente) {
			log.info("Actulizando un automovil");
			this.repo.update(existente);
		}
		
		@Override
		public void darDeBaja(int id) {
			log.info("Eliminando un Automovil");
			this.repo.delete(id);
		}
		
		@Override
		public List<Automovil> obtenerTodos(){
			log.info("Mostrando todos los automoviles");
			/*
			List<Automovil> misAutosFavoritos = new ArrayList<>();
			misAutosFavoritos.add(new Automovil("Seat","Ibiza",1982));
			misAutosFavoritos.add(new Automovil("Seat","Malaga",1986));
			misAutosFavoritos.add(new Automovil("Seat","Toledo",1988));
			misAutosFavoritos.add(new Automovil("Seat","Alhambra",1996));
			return misAutosFavoritos;
			*/
			return this.repo.findAll();
			
		}
		
		@Override
		public Automovil obtenerPorId(int id) {
			log.info("Motrando el Automovil por id");
			/*
			Automovil result= new Automovil("Mercedes","SDK",1960);
			result.setId(id);
			return result;
			*/
			return this.repo.findById(id);
		}
}
