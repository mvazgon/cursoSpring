package org.itnow.cursoSpring.clasecuatro.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor


/*
 * En esta superclase definimos que la propiedad id se almacena en la base de datos con las anotaciones @id y @Generate...
 */
public abstract class ObjetoModels {

	@Getter @Setter
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
}
