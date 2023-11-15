package org.itnow.cursoSpring.claseUno.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.itnow.cursoSpring.claseUno.models.Cliente;
import org.itnow.cursoSpring.claseUno.utils.Logger;

/*
 * clase para guardar datos en BBDD, nos dá los métodos que necesitaremos para poder interactuar con la BBDD
 * implementa una interace llamada ClienteRepository donde sobreescribimos los métodos fundamentales de la misma. 
 */
public class ClienteDatabaseRepository implements ClienteRepository{

	/*
	 * Definimos en las propiedades del objeto, aquellos objetos que manipularemos para ofrecer 
	 * a los objetos de la clase service la persistencia de esta clase, 
	 * en etse caso es una persistencia de BBDD SQLlite.
	 * También necesitamos una propiedad que almacene un objeto de tipo logger
	 * definimos asi mismo un string(literal) con la cadena de conexión a la BBDD, se debe de hacer en el config.xml pero lo hemos traido por ahora aquí 
	 */
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private Logger logger;
	private String jdbcdriver="jdbc:sqlite:clientes.db";
	private Connection conn=null;
	
	/*
	 * Constructor de la clase,
	 * a. se le pasa como argumento un objeto Logger para escribir en un log.
	 */
	public ClienteDatabaseRepository(Logger logger) {
		this.logger = logger;
	//	this.jdbcdriver = jdbcdriver; // le pasamos el driver. 
	//	this.clientes.add(new Cliente(1,"Alicia"));
	//	this.clientes.add(new Cliente(2,"Manuel"));
		
		/*
		 * Creamos una conexión a la BBDD e intentamos comprobar que la tabla que necesitamos esté creada. 
		 */
		try {
			this.conn = DriverManager.getConnection(this.jdbcdriver);
			java.sql.Statement sql= conn.createStatement();
			sql.execute(""" 
					CREATE TABLE IF NOT EXISTS CLIENTE (
					id INTEGER PRIMARY KEY,
					doc INTEGER NOT NULL, 
					name TEXT NOT NULL
					);
					""");
		}catch (Exception e) {
			logger.log("Error al conectar a la BBDD "+ e.getMessage());
			throw new Error(e.getMessage());
		}
	}
	
	/*
	 * Sobreescribimos el método insert,
	 * instertamos un registro con los datos que pasamos con el objeto Cliente. 
	 * De este objeto recupero los datos con los getters de cada propiedad interna que posee.
	 * es un método que no devuelve ningún resultado  
	 */
	@Override
	public void insert(Cliente cliente) {
		// TODO Auto-generated method stub
		//System.out.println("Inserto un cliente en la BBDD");
		/*
		 * Em
		 */
		try {
			java.sql.Statement sqlInsert = conn.createStatement();
			sqlInsert.execute(String.format(""" 
					INSERT INTO Cliente(doc,name)
					values (%d,%s);""",cliente.getDocumento(),cliente.getNombre())) ;
		}
		catch (Exception e) {
			this.logger.log("Error al insertar "+ e.getMessage());
			throw new Error(e.getMessage());
		}
		this.logger.log("Inserto un cliente en la BBDD");
	}

	/*
	 * Este método no acepta parámetros, devuelve una lista de objetos cliente que se puede iterar. 
	 */
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		//System.out.println("Recupero todos los clientes de la BBDD");
		try {
		    java.sql.Statement sqlSelect = conn.createStatement();
			ResultSet qry = sqlSelect.executeQuery("Select * from cliente");
			while (qry.next()) {
				Cliente c= new Cliente(
						qry.getInt("doc"),
						qry.getString("name"));
				this.clientes.add(c);
			}
		}catch (Exception e) {
			this.logger.log("Error al hacer un  a la BBDD"+e.getMessage());
			throw new Error(e.getMessage());
		}
		
		this.logger.log("Recuppero todos los clientes de la base de datos"); 
		
		
		return this.clientes;
	}

	/*
	 * No tiene ahora mucho sentido este método. 
	 */
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;	
		//this.logger.log("Inserto un cliente en la bbdd");
	}
	
	/*
	 * Este método nos permite cerrar la conexión con la BBDD. 
	 */
	public void destruirBean() {
		logger.log("Destruyendo el Bean");
		if (conn!=null) {
			try {
				conn.close();
			}catch (SQLException e) {
				logger.log("Error al destruir el Bean");
			}
		}
	}
}
