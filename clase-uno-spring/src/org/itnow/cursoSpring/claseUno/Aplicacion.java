package org.itnow.cursoSpring.claseUno;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.itnow.cursoSpring.claseUno.controllers.ClienteController;
import org.itnow.cursoSpring.claseUno.models.Cliente;
import org.itnow.cursoSpring.claseUno.models.DemoBean;
import org.itnow.cursoSpring.claseUno.repository.ClienteDatabaseRepository;
import org.itnow.cursoSpring.claseUno.repository.ClienteMocRepository;
import org.itnow.cursoSpring.claseUno.services.ClienteService;
//importamos de las librerias de spring
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Aplicacion {

	public static void main(String[] args) {
	/*	// Imprimimos por consola. 
		System.out.println("Hola javateros.");
		//instanciamos un objeto de la libreria spring core.
		ApplicationContext a;
	*/
		/*
		//ejemplo con pojos
		DemoBean demo= new DemoBean();
		demo.setTexto("Hola mundo en spring");
		System.out.print(demo.getTexto());
		*/
		
		/*Ejemplo con spring
		
		 * Se ha definido un modelo -- DemoBean.java y lo importamos
		 * Se ha definido un archivo SpringConfig.xml q se puede usar la clase org.itnow.cursSpring.claseUno.models.DemoBean y definirlo con un param inicial
		 * Se ha definido un objeto iocContainer que instancia la clase ClaassPathXmlApplicationContext que carga el archivo SpringConfig.xml y los objetos definidos en el.
		 * Se intancia a partir del iocContainer el objeto demoBean con el nombre "demo" y se tira 
		
		ApplicationContext iocContainer = new ClassPathXmlApplicationContext("SpringConfig.xml");
		DemoBean demo = (DemoBean)iocContainer.getBean("demoBean");
		System.out.println(demo.getTexto());
		*/
		
		/* Ejemplo 3 - Arqutectura de referencia sin spring
		 * 
		 *
		Cliente c = new Cliente(1,"Juan");
		ClienteService service = new ClienteService();
		service.agregarCliente(c);
		*/
		
		/* Ejemplo 4 - inyección de dependencia por constructor
		 * 
		 *
		Cliente c= new Cliente(1,"Juan");
		//ClienteDatabaseRepository repository = new ClienteDatabaseRepository();
		ClienteMocRepository repository = new ClienteMocRepository();
		ClienteService  service = new ClienteService(repository);
		service.agregarCliente(c);
		*/
		
		/*
		 * Ejemplo 5 - inyección de dependencias por setter
		 * 
		 *
		 
		Cliente c= new Cliente(1,"Juan");
		//ClienteDatabaseRepository repository = new ClienteDatabaseRepository();
		ClienteMocRepository repository = new ClienteMocRepository();
		ClienteService  service = new ClienteService();
		service.setRepository(repository); //<<<< setter inyection 
		service.agregarCliente(c);
		*/
		
		/*
		 * Ejemplo 6 - Inyeción por constructor con spring
		 * 
		 *
		ApplicationContext iocContainer = new ClassPathXmlApplicationContext("SpringConfig.xml");
		ClienteService  service = (ClienteService)iocContainer.getBean("clienteService");
		Cliente c = new Cliente(1,"Juan");
		service.agregarCliente(c);
		*/
		
		/*
		 * Ejemplo 7 - Inyección por setter con spring
		 * 
		 *
		 ApplicationContext iocContainer= new ClassPathXmlApplicationContext("SpringConfig.xml");
		 ClienteController controller = (ClienteController)iocContainer.getBean("clienteController");
		 System.out.println("Simulo un http get a /cliente");
		 System.out.println(controller.index());
		 */
		
		/*
		 * Ejemplo 8 - 
		 *  visitar la página de SQLite www.sqlitutorial.net/sqlite-java/
		 *  
		 *
		Connection conn=null;
		try {
			try {
				
				conn = DriverManager.getConnection("jdbc:sqlite:clientes.db");
				java.sql.Statement sql= conn.createStatement();
				sql.execute(""" 
						CREATE TABLE IF NOT EXISTS CLIENTE (
						id INTEGER PRIMARY KEY,
						doc INTEGER NOT NULL, 
						name TEXT NOT NULL
						);
						""");
				java.sql.Statement sqlInsert = conn.createStatement();
				sqlInsert.execute(""" 
						INSERT INTO Cliente(doc,name)
						values (1,"Juan"),(2,"Manolo")
					;
						""");
			    java.sql.Statement sqlSelect = conn.createStatement();
				ResultSet qry = sqlSelect.executeQuery("Select * from cliente");
				while (qry.next()) {
					System.out.println(String.format("%d - %s", 
							qry.getInt("doc"),
							qry.getString("name")));
				
				}
			}catch (Exception ex) {
				System.out.println("Error "+ex.getMessage());
			}
		}
		finally {
			if (conn!=null) {
				try {
					conn.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		*/
		/*
		 * Ejemplo 9 - Ya está en la lógica la recuperación de los datos de la BBDD
		 * 
		 */
		//Creamos un objeto que recupera su config para crear BEANS del archivo SpringConfig.xml
		ApplicationContext iocContainer= new ClassPathXmlApplicationContext("SpringConfig.xml");
		// Creamos un objeto con nombre Controller y forzamos que sea del tipo ClienteController invocando un BEAN llamado clienteController - que sea ha definido en el xml previamente.
		ClienteController controller = (ClienteController)iocContainer.getBean("clienteController");
		//imprimos los resultados sabiendo que el controller tine un métod INDEX que devuelve una cadena de texto. 
		System.out.println("Simulo un http get a /cliente");
		System.out.println(controller.index());
	}
}