package org.itnow.cursoSpring.clasecuatro.repository;

import java.util.*;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.itnow.cursoSpring.clasecuatro.models.Automovil;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("Test")

public class AutomovilMockRespository implements AutomovilRepository {
	
	private List<Automovil> automoviles = new ArrayList<Automovil>() ;
	private static int ULTIMO_ID=0;
	private static int nextId() {
		AutomovilMockRespository.ULTIMO_ID++;
		return AutomovilMockRespository.ULTIMO_ID;
	}
	
	private Log log = LogFactory.getLog(AutomovilMockRespository.class);
	
	public AutomovilMockRespository() {
		
		log.info("Creando el array de autos");
		
		Automovil auto = new Automovil();
		auto.setId(AutomovilMockRespository.nextId());
		auto.setMarca("Seat");
		auto.setModelo("Ibiza");
		auto.setAño(1982);
		this.automoviles.add(auto);
		
		auto = new Automovil();
		auto.setId(AutomovilMockRespository.nextId());
		auto.setMarca("Seat");
		auto.setModelo("Malaga");
		auto.setAño(1986);
		this.automoviles.add(auto);
		
		auto = new Automovil();
		auto.setId(AutomovilMockRespository.nextId());
		auto.setMarca("Seat");
		auto.setModelo("Toledo");
		auto.setAño(1988);
		this.automoviles.add(auto);
		
		auto = new Automovil();
		auto.setId(AutomovilMockRespository.nextId());
		auto.setMarca("Seat");
		auto.setModelo("Alhambra");
		auto.setAño(1996);
		this.automoviles.add(auto);
		
		auto = new Automovil();
		auto.setId(AutomovilMockRespository.nextId());
		auto.setMarca("Seat");
		auto.setModelo("Exeo");
		auto.setAño(2000);
		this.automoviles.add(auto);
		
		auto = new Automovil();
		auto.setId(AutomovilMockRespository.nextId());
		auto.setMarca("Seat");
		auto.setModelo("Tarraco");
		auto.setAño(2014);
		this.automoviles.add(auto);
		
		auto = new Automovil();
		auto.setId(AutomovilMockRespository.nextId());
		auto.setMarca("Seat");
		auto.setModelo("Arona");
		auto.setAño(2012);
		this.automoviles.add(auto);
		
	}

	@Override
	public void insert(Automovil auto) {
		// TODO Auto-generated method stub
		log.info("Insertando nuevo auto");
		this.automoviles.add(auto);
		
	}

	@Override
	public void update(Automovil auto) {
		// TODO Auto-generated method stub
		log.info("Actualizando auto");
		Automovil encontrado = this.automoviles.stream().filter(a -> (a.getId()== auto.getId())).findFirst().orElse(null);
		
		/* Esto foreach comentado es la parte del (a -> (a.getId()==auto.getId())
		 * Automovil enontrado = null;
		for (Automovil actual : automoviles) {
			if (actual.getId()==automovil.getId()) {
				encontrado = actual;
			}
		}*/
		
		if (encontrado != null) {
			this.automoviles.remove(encontrado);
			this.automoviles.add(encontrado);
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		log.info("Borrando auto");
		Automovil encontrado = this.automoviles.stream().filter(a -> (a.getId() == id)).findFirst().orElse(null);
		if (encontrado != null) {
			this.automoviles.remove(encontrado);
		}
	}

	@Override
	public List<Automovil> findAll() {
		// TODO Auto-generated method stub
		log.info("Recuperando todos los autos");
		return this.automoviles;
	}

	@Override
	public Automovil findById(int id) {
		// TODO Auto-generated method stub
		log.info("Recuperando un auto");
		return this.automoviles.stream().filter(a -> (a.getId()== id)).findFirst().orElse(null) ;
	}
}
