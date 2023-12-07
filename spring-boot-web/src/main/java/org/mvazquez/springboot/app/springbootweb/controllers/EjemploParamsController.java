package org.mvazquez.springboot.app.springbootweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class EjemploParamsController {

	@GetMapping("/string")
	public String param(@RequestParam(name="texto",required=false, defaultValue="Ninguno") String texto, Model model) {
		model.addAttribute("titulo", "Recibir paramentros en la url");
		model.addAttribute("resultado", "El texto enviado es:"+texto);		
		return "params/ver";
	}
	
	@GetMapping("/")
	public String index() {
		return "params/index";
	}
	
	@GetMapping("/mix-params")
	public String param(@RequestParam(name="saludo", required=false, defaultValue="Ninguno") String saludo,
						@RequestParam(name="numero", required=false, defaultValue="0") String numero,
						Model model) {
		model.addAttribute("titulo", "Recibir paramentros en la url");
		model.addAttribute("resultado","El saludo es: "+saludo+"; y el número es: "+ Integer.parseInt(numero));
		return "params/ver";
	}
	
}