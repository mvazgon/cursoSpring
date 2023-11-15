package org.itnow.cursoSpring.clasecuatro.controllers;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.itnow.cursoSpring.clasecuatro.models.Alumno;

/*
 * Nota: Los controles gestionan las respuestas a las peticiones de los usuarios.
 * asociamos estos métodos con esta etiqueta a la carpeta templates
 */
@Controller()
public class HomeController {
	
	/*
	 * El nombre del template .html tiene que coincidir con el String del return. 
	 */
	@GetMapping("/")
	public String index(Model modelo) {
		modelo.addAttribute("titulo","Bienvenido a mi aplicación");
		List<Alumno> alumnos = new ArrayList<Alumno>();
		
		Alumno alumno1= new Alumno();
		alumno1.setNombre("Manuel");
		alumno1.setAppelido("Vazquez");
		alumnos.add(alumno1);
		
		Alumno alumno2= new Alumno();
		alumno2.setNombre("Javier");
		alumno2.setAppelido("Morcillo");
		alumnos.add(alumno2);
		
		Alumno alumno3= new Alumno();
		alumno3.setNombre("Javier");
		alumno3.setAppelido("Contreras");
		alumnos.add(alumno3);
		
		Alumno alumno4= new Alumno();
		alumno4.setNombre("Jonatan");
		alumno4.setAppelido("Sánchez");
		alumnos.add(alumno4);
		
		return "index";
	}

}
