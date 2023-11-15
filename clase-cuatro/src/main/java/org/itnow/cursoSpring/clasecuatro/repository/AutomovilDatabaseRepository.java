package org.itnow.cursoSpring.clasecuatro.repository;

import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.itnow.cursoSpring.clasecuatro.models.Automovil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/*
 * Con esta anotaciones definimos que la clase es un repository para manejar persistencia
 * Y que se aplica cuando la app se ejecuta con valor de la var: spring.profiles.active="Produccion"; esta var está definida en el archivo application.properties/yaml con su formato correspondiente. 
 */

@Repository
@Profile("Produccion")

/*
 * La clase implementa la clase AutomovilRepository que es una clase abstracta para definir los métodos por defecto y posteriormente sobreescribirlos. 
 */
public class AutomovilDatabaseRepository implements AutomovilRepository{
	
	/*
	 * En esta propiedad almacenamos un objeto log sobre la clase AutomovilDataRepository
	 */
	private Log log = LogFactory.getLog(AutomovilDatabaseRepository.class);
	
	/*
	 * Con la anotación en esta propiedad instanciamos un objeto repository
	 */
	@Autowired
	private AutomovilJPARepository repository;

	/*
	 * Sobreescribimos el método insert de la clase asbtracta, ejecutando sobre la propiedad/objeto repository el método save
	 */
	@Override
	public void insert(Automovil auto) {
		// TODO Auto-generated method stub
		log.info("Add auto");
		this.repository.save(auto);
		
	}

	/*
	 * Sobreescribimos el método insert de la clase asbtracta, ejecutando sobre la propiedad/objeto repository el método update
	 */
	@Override
	public void update(Automovil auto) {
		// TODO Auto-generated method stub
		log.info("Update auto");
		this.repository.save(auto);
	}

	/*
	 * Sobreescribimos el método insert de la clase asbtracta, ejecutando sobre la propiedad/objeto repository el método delete
	 */
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		log.info("Delete auto");
		this.repository.deleteById(id);
	}

	/*
	 * Sobreescribimos el método insert de la clase asbtracta, ejecutando sobre la propiedad/objeto repository el método finAll() devolviendo un ArrayList de objetos Automovil.
	 */
	@Override
	public List<Automovil> findAll() {
		// TODO Auto-generated method stub
		log.info("Find all auto");
		return this.repository.findAll();
	}
	
	/*
	 * Sobreescribimos el método insert de la clase asbtracta, ejecutando sobre la propiedad/objeto repository el método finAll() devolviendo un objeto Automovil
	 */
	@Override
	public Automovil findById(int id) {
		// TODO Auto-generated method stub
		log.info("Find by id");
		return this.repository.findById(id).orElse(null);
	}
}
