package org.itnow.cursoSpring.clasecuatro.models;

import lombok.*;

/*
 * Estas anotaciones nos permiten introducir:
 * ToString() -- método de conversión de propiedades a cadena
 * AllargsConstructor -- método de construcción de objeto por parámetros
 * NoArgsConstructor -- método de construcción de objeto sin parámetros
 * La clase Alumno se le establecer dos propiedades:
 * 	nombre
 *  apellido
 * 
 */

@ToString()
@AllArgsConstructor()
@NoArgsConstructor()
public class Alumno {

		/*
		 * Con las anotaciones Getter y Setter se les crea automáticamente los métodos get y set del parámetro. 
		 */
		@Getter @Setter
		private String nombre;
		
		@Getter @Setter
		private String appelido;
}
