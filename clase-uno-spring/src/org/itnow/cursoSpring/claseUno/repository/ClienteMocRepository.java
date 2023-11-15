package org.itnow.cursoSpring.claseUno.repository;

import java.util.ArrayList;
import java.util.List;

import org.itnow.cursoSpring.claseUno.models.Cliente;
import org.itnow.cursoSpring.claseUno.utils.Logger;

//class for to do Mocks check emulate operations with DDBB in memory
public class ClienteMocRepository implements ClienteRepository {

	private List<Cliente> clientes = new ArrayList<Cliente>();
	private Logger logger;
	
	@Override
	public void insert(Cliente cliente) {
		// TODO Auto-generated method stub
		//System.out.println("Mockeo la inserción de cliente");
		this.logger.log("Inserción de cliente");
		this.clientes.add(cliente);
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		//System.out.println("Mockeo recuperar los clientes");
		this.logger.log("Mockeo recuperar los clientes");
		return this.clientes;
	}
}
