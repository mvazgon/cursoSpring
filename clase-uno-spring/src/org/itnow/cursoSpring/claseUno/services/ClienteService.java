package org.itnow.cursoSpring.claseUno.services;

/*
 * Importamos la clase java.utils.list para manejar listas
 * importamos la clase models.Cliente para gestionar un solo registro.
 * Importamos las clases del paquete repository para interactuar con las diferentes persistencias. 
 */
import java.util.List;
import org.itnow.cursoSpring.claseUno.models.Cliente;
import org.itnow.cursoSpring.claseUno.repository.*;

/*
 * Esta clase nos es util para interactuar con los clientes y los mecanismos de persistencia.
 */
public class ClienteService {

	/*
	 * Con esta propiedad preparamos el objeto para manejar la persistencia
	 */
	private ClienteRepository repository;

	// aquí va el código de las líneas siguientes a: 50
	/*
	 * Este método nos define, por argumento el tipo de repositorio
	 */
	public void setRepository(ClienteRepository repo) {
		this.repository = repo;
	}
	
	/*
	 *  Método para agregar Clientes, 
	 *  a. usamos el objeto Cliente como argumento y 
	 *  b. usamos del repositorio el método insert para poder almacenarlo en el método persistencia 
	 */
	public void agregarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		this.repository.insert(cliente);
	}
	
	/*
	 * Método para recuperar Clientes
	 * a. usamos del repositorio el método findall() será diferente según el tipo de repositorio seleccionado.
	 */
	public List<Cliente> recuperarClientes() {
		return this.repository.findAll();
	}
}


/* Ejemplo 1.
//sin inyección de dependencias
private ClienteRepository repository;
/*
public ClienteService() {
	this.repository = new ClienteDatabaseRepository();
	this.repository = new ClienteMocRepository();

}
*/

/*
 * Ejemplo 2. Inserción por constructor
 * 
 * 
private ClienteRepository repository;

public ClienteService(ClienteRepository repo) {
	this.repository = repo ;
}

 * 
 *

// Ejemplo 3. Por setter 
private ClienteRepository repository;

public void setRepository(ClienteRepository repo) {
	this.repository = repo;
}	
*/

// Ejemplo 4. Por constructor con spring.



/* 
 * Ejemplo 5 - Constructor y setter
 * 
 */
/*	public ClienteService(ClienteRepository repo) {
	this.repository = repo ;
}
*/
