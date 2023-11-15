package org.itnow.cursoSpring.claseUno.controllers;

import org.itnow.cursoSpring.claseUno.*;
import org.itnow.cursoSpring.claseUno.services.ClienteService;

//Esta clase captura las peticiones http y construye la salida
public class ClienteController {
	
	/*
	 * Con esta propiedad almacenamos un servicio, 
	 * es la clase q nos permite construir los objetos Cliente y la Lista de Clientes para recuperar los datos dependientemente de la capa de persistencia
	 *  que hemos definido en el repository  
	 */
	private ClienteService  service;
	
	/*
	 * Construimos el objeto pasando como argumento el objeto service que almacenamos en la propiedad correspondiente.
	 */
	public void setService(ClienteService service) {
		this.service = service;
	}
	
	//@PeticionesGet("/clientes")
	/*
	 * Construimos la salida del programa con un objeto StringBuilder, que trata las cadenas como si fuera un array.
	 * Vamos añadiendo información según necesitemos. 
	 * usamos el método recuperarClientes del service para iterar sobre la lista que nos devuelve y construimos una cadena formateada con parte de las propiedades de cada cliente.  
	 */
	public String index() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>\n");
		sb.append("<head>\n");
		sb.append("</head>\n");
		sb.append("<body>\n");
		sb.append("<H1> Mi página principal de clientes</h1>\n");
		this.service.recuperarClientes().forEach(c->{
			sb.append(String.format("<li>%d - %s</li>\n",c.getDocumento(), c.getNombre()));
		});
		sb.append("</ul>\n");
		sb.append("</body>\n");
		sb.append("</html>\n");
		return sb.toString();
	}
}
