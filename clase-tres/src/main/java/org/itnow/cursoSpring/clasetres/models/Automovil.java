package org.itnow.cursoSpring.clasetres.models;

import lombok.*;

@ToString()
public class Automovil {
	/*
	 * Definimos las propiedades.
	 * Decoramos las propiedades con Getter y Setter de lombok para definir métodos Getter y Setter 
	 * simples.  
	 */
	@Getter @Setter
	private String marca;
	
	@Getter @Setter
	private String modelo;
	
	@Getter @Setter
	private int año;
}
