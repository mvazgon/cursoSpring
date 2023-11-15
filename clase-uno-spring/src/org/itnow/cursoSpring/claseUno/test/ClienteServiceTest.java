package org.itnow.cursoSpring.claseUno.test;

import static org.junit.jupiter.api.Assertions.*;

import org.itnow.cursoSpring.claseUno.models.Cliente;
import org.itnow.cursoSpring.claseUno.repository.ClienteRepository;
import org.itnow.cursoSpring.claseUno.services.ClienteService;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class ClienteServiceTest {
	
	private ClienteService service;
	private ClienteRepository repository;
	
	@BeforeEach
	void beforeTest() {
		@SuppressWarnings("resources")
		ApplicationContext iocContainer = new ClassPathXmlApplicationContext("SpringTestConfig.xml");
		this.service = (ClienteService)iocContainer.getBean("clienteService");
	}
	
	@Test
	void testSumar() {
		int num1=2;
		int num2=2;
		assertEquals(4, num1+num2);
	}
	
	@Test
	void testAgregarCliente() {
		/*ApplicationContext iocContainer = new ClassPathXmlApplicationContext("SpringTestConfig.xml");
		ClienteService  service = (ClienteService)iocContainer.getBean("clienteService");
		*/
		//Test de recuperar
		assertEquals(0, this.service.recuperarClientes().size());
		
		Cliente c = new Cliente(1,"Juan");
		service.agregarCliente(c);
		
		//Test 
		assertEquals(1, this.service.recuperarClientes().size() );
	}

	@Test
	void testAgregarVariosClientes() {
		/*
		ApplicationContext iocContainer = new ClassPathXmlApplicationContext("SpringTestConfig.xml");
		ClienteService  service = (ClienteService)iocContainer.getBean("clienteService");
		*/
		//Test de recuperar
		assertEquals(0, this.service.recuperarClientes().size());
		
		this.service.agregarCliente(new Cliente(1,"Juan"));
		this.service.agregarCliente(new Cliente(2,"Manuel"));
		this.service.agregarCliente(new Cliente(3,"Javier"));
		this.service.agregarCliente(new Cliente(4,"Francisco"));
		//
		assertEquals(4, service.recuperarClientes().size());
	}
}
