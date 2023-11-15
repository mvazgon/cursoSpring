package org.itnow.cursoSpring.clasecuatro.models;

import jakarta.persistence.*;
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
/*
 * En el modelo especificamos el mapeo de persistencia. De forma que indicamos que es una entidad de BBDD y la tabla, nombrándola, en la que se almacena dicha entidad. 
 */
@Entity
@Table(name="automovil")
//public class Automovil extends ObjetoModels{
	/*
	 * Definimos las propiedades.
	 * Decoramos las propiedades con Getter y Setter de lombok para definir métodos Getter y Setter 
	 * simples.  
	 * También definimos un mapeo entre las propiedades de la clase y las columnas de la tabla.
	 * en la superclase tenemos que establecer el id del registro.
	 */
	
public class Automovil {
	
	/* Con esta anotaciones hacemos:
	 * Establecemos:
	 *  metodos get y set para la propiedad
	 *  que la propiedad tiene (dentro de la entidad) el caracter de una columna en BBDD de tipo ID
	 *  Y q el valor se genera de forma automática como un autoincremental
	 */
	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/*
	 * Con estas anotaciones establecemos:
	 *  los métodos get y set de la misma
	 *  que la propiedad está asignada en la tabla correspondiente con la columna marca
	 */
	@Getter @Setter
	@Column(name="marca")
	private String marca;

	/*
	 * Con estas anotaciones establecemos:
	 *  los métodos get y set de la misma
	 *  que la propiedad está asignada en la tabla correspondiente con la columna modelo
	 */
	@Getter @Setter
	@Column(name="modelo")
	private String modelo;
	
	/*
	 * Con estas anotaciones establecemos:
	 *  los métodos get y set de la misma
	 *  que la propiedad está asignada en la tabla correspondiente con la columna year
	 */
	@Getter @Setter
	@Column(name="year")
	private int año;
}
