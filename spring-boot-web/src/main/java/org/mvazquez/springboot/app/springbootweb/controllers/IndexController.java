package org.mvazquez.springboot.app.springbootweb.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.mvazquez.springboot.app.springbootweb.models.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller //con esta etiqueta decoreamos para que sea considerada esta clase como un controlador
@RequestMapping("/app") // esto nos permite agregar  un contexto 
public class IndexController {

	/*
	 * Diferentes etiquetas que podemos aplicar 
	 */
	//@GetMapping / @PostMapping /@DeleteMapping /@PutMapping
	/*
	 * Las formas más simples
	 */
	//@GetMapping({"/index","/","/home"})
	/* 
	 * La forma más formal
	 */
	//@RequestMapping(path={"/index","/","/home"},method=RequestMethod.GET)
	//public String index(Model model) {
	//public String index(ModelMap model) {
	@RequestMapping(path="/index",method=RequestMethod.GET)
	public String index(Map<String, Object> map) {
		map.put("titulo","Hola SpringBoot con Map");
		return "index";
	}
	/*
	 * Mas usada, recibimos un objeto Model y este nos devuelve la info para imprimir en el templaet
	 */
	@GetMapping({"/",""})
	public String index(Model model) {
		model.addAttribute("titulo", "Hola SpringBoot con Model");
		return "index";
	}
	
	/*
	 * Menos usada, pero similar a la anterior. En este usamos un objeto ModelMap
	 */
	@GetMapping("/home")
	public String index(ModelMap model) {
		model.addAttribute("titulo", "Hola SpringBoot con ModelMap");
		return "index";
	}
	
	/*
	 * En este manejamos un objeto ModelAndView
	 */
	@GetMapping("/mv")
	public ModelAndView index(ModelAndView mv) {
		mv.addObject("titulo","Hola SpringBoot M&V");
		mv.setViewName("index");
		return mv;
	}
	
	/*
	 * Aquí usamos un modelo de objeto(Usuario)para la info y un Model para devolver la info al template
	 */
	
	@RequestMapping(path="/perfil",method=RequestMethod.GET)
	public String perfil(Model model) {
		/*
		 * Creamos la instancia de la clase usuario 
		 */
		Usuario usuario = new Usuario();
		usuario.setNombre("Manuel"); 
		usuario.setApellido("Vazquez");
		usuario.setEmail("absimiliard@hotmail.com");
		/*
		 * Establecemos la información a devolver al template html
		 */
		model.addAttribute("titulo", "Perfil del usuario".concat(usuario.getNombre()));
		model.addAttribute("usuario",usuario);
		
		/*
		 * indicamos el nombre del template a usar.
		 */
		return "perfil";
	}
	
	@RequestMapping(path="/listar",method=RequestMethod.GET)
	public String listar(Model model) {
		
		/*
		 * List<Usuario> usuarios = new ArrayList<>();
		 
		usuarios.add(new Usuario("Manuel","Vázquez","absimiliard@mail.com"));
		usuarios.add(new Usuario("Yolanda","Sánchez","brisca@mail.com"));
		usuarios.add(new Usuario("Amadeo","Vázquez","amadeo.vazquez.sanchez@mail.com"));
		*/
		
		/*
		List<Usuario> usuarios = Arrays.asList(new Usuario("Manuel","Vázquez","absimiliard@mail.com"),
				new Usuario("Yolanda","Sánchez","brisca@mail.com"),
				new Usuario("Amadeo","Vázquez","amadeo.vazquez@mail.com"));
		*/
		List<Usuario> usuarios = this.poblarUsuarios();
		
		model.addAttribute("titulo", "Listado de usuarios");
		//model.addAttribute("usuarios", usuarios);
		
		return "listar";
	}
	
	/*
	 * con esta etiqueta creamos un método que en todas aquellas vista que aparezca el <table th:if="${!usuarios.isEmpty()}"> ; si el objeto no está vacio rellena la tabla. 
	 */
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios(){
		List<Usuario> usuarios = Arrays.asList(new Usuario("Manuel","Vázquez","absimiliard@mail.com"),
				new Usuario("Yolanda","Sánchez","brisca@mail.com"),
				new Usuario("Amadeo","Vázquez","amadeo.vazquez@mail.com"));
		return usuarios;
	}
}
