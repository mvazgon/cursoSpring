package org.itnow.cursoSpring.clasetres.controllers;

//Clases generales para gestionar errores, elementos comunes del app y ssl
import java.io.IOException;
import java.util.*;
import javax.net.ssl.SSLEngineResult.Status;
// Clase para interactuar con http
import org.springframework.http.*;
//importamos estas clases para poder anotar
import org.springframework.web.bind.annotation.*;
//Clase para manejar respuestas y objetos dentro de request HTTP
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//clase para el log
import org.apache.juli.logging.*;

/*
 * Clase para crear objetos tipo automóvil
 */
import org.itnow.cursoSpring.clasetres.models.Automovil;

//Anotamos esta clase al ser un controlador con la etiqueta RESTController
@RestController
public class homeController {
	
	/*
	 * Definimos una propiedad que es un objeto log y que está asociado a esta clase. 
	 */
	private Log logger = LogFactory.getLog(homeController.class);

	/*
	 * Métodos GET
	 * 
	 */
	
	//Esta anotación nos permite capturar peticiones GET y devolvemos un string con la info q deseamos. 
	@GetMapping("/")
	public String index() {
		logger.info("Se llama al index"); 
		return "Hola Mundo con HTML";
	}
	
	/*
	 * Esta anotación nos permite una forma más explicita
	 * Definir el endpoint
	 * Definir el método http que captura.
	 */
	
	@RequestMapping(value = "/html", method=RequestMethod.GET)
	public String html() {
		return "<h1>Hola Mundo con HTML</h1>";
	}
	
	/*
	 * Este anotación es igual a la primera pero devolvemos un json
	 */
	@GetMapping("/auto")
	public Object jsonDemo() {
		Automovil auto = new Automovil();
		auto.setMarca("Fiat");
		auto.setModelo("600");
		auto.setAño(1969);
		return auto;
	}
	
	/*
	 * La anotación es como la segunda. 
	 * En la definición del método EL parametro vá decorado, indicando:
	 * - el nombre del parámetro a establecer en la url y
	 * - definimos también el nombre 
	 * 	 
	 */
	@RequestMapping(value="/saludo", method = RequestMethod.GET)
	public String saludo(@RequestParam("nombre") String nombre) {
		return "Hola "+nombre;
	}
	
	/*
	 * son valores por defecto.
	 */
	@RequestMapping(value="/salu2", method = RequestMethod.GET)
	public String salu2( @RequestParam( name="nombre", defaultValue = "Mundo") String nombre) {
		return "Hola "+nombre;
	}
	
	/*
	 * Toma el nombre del parámetro de
	 */
	@RequestMapping(value="/salu3", method = RequestMethod.GET)
	public String salu3(@RequestParam() String nombre) {
		return "Hola "+nombre;
	}
	
	@RequestMapping(value="/salu4", method = RequestMethod.GET)
	public String salu4(@RequestParam() Optional<String> nombre) {
		return "Hola "+nombre.orElse("Mundo");
	}
	
	@RequestMapping(value="/params", method = RequestMethod.GET)
	public String parametros(@RequestParam() Map<String,String> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("Los parametros son:\n");
		sb.append("<ul>");
		params.forEach((clave,valor) -> {
				sb.append(String.format("<li>%s=%s</li>",clave,valor));
		});
		sb.append("</ul>");
		return sb.toString();
	}
	
	@RequestMapping(value="/param2s", method = RequestMethod.GET)
	public String parametro2(@RequestParam("nombre") List<String> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("Los parametros son:\n");
		sb.append("<ul>");
		params.forEach((valor) -> {
				sb.append(String.format("<li>%s</li>",valor));
		});
		sb.append("</ul>");
		return sb.toString();
	}
	
	@RequestMapping(value="/salu5/{nombre}", method = RequestMethod.GET)
	public String salu5(@PathVariable("nombre") String nombre) {
		return "Hola "+ nombre;
	}
	
	/*
	 * Mecanismos para métodos POST
	 * 
	 */
	@PostMapping("/saludospost")
	public String saludosPost(@RequestBody() String nombre) {
		return "hola " + nombre; 
	}
	
	@PostMapping("/jsonpost")
	public String jsonPost(@RequestBody() Automovil auto) {
		return "Manejo un: " + auto.toString();
	}
	
	
	/*
	 *  Mecanismo para recuperar los Headers
	 */
	@GetMapping("/headers")
	public String headear(@RequestHeader() Map<String,String> headers) {
		StringBuilder sb = new StringBuilder();
		sb.append("Los parametros son:\n");
		sb.append("<ul>");
		headers.forEach((clave,valor) -> {
				sb.append(String.format("<li>%s=%s</li>",clave,valor));
		});
		sb.append("</ul>");
		return sb.toString();
	}
	
	/*
	 * Gestión de las respuetas.
	 *  
	 */
	@GetMapping("/secret")
	public ResponseEntity<String> secret(@RequestParam("password") String password){
		logger.info("Piden validar.");
		if (password.equals("sesamo")){
			return new ResponseEntity<>("Acceso Concedido",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
	} 
	
	@GetMapping("/404")
	public ResponseEntity<String> cuatrocerocuatro(){
		logger.info("Piden un 404");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/dividir")
	public ResponseEntity<String> dividir(
			@RequestParam() Double numerador,
			@RequestParam() Double divisor ){
		logger.info("Estamos dividiendo");
		/*
		if (divisor<=0) {
			return ResponseEntity.internalServerError().body("No se puede dividir por 0");
		}
		Double resultado = numerador/divisor;
		return new ResponseEntity<String>(resultado.toString(), HttpStatus.OK);
		*/
		try {
			Double resultado = numerador/divisor;
			return new ResponseEntity<String>(resultado.toString(), HttpStatus.OK);
		}catch (Error e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@GetMapping("/servlet")
	public void servletResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("Request a: "+ request.getRequestURI());
		response.setHeader("myHeader", "valor");
		response.setStatus(200);
		response.getWriter().println("<h1>Asi se trabaja con servlet originalmente</h1>");
	}
	
	/*
	 * Provocamos en este método un error que será capturado por defecto en el siguiente método que gestiona TODOS los errores de la app por defecto. 
	 */
	@GetMapping("/errorsin")
	public ResponseEntity<String> error(){
		throw new Error("Este es un error adrede");
	}
	
	/*
	 * Método genérico cuando falla cualquier método anteriormente definido. 
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Error.class)
	public ResponseEntity<String> erroresController(){
		return ResponseEntity.internalServerError().build("Se ha producido un error inesperado...");
	}
	
	
}
