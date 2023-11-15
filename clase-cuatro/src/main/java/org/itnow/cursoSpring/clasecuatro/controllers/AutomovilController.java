package org.itnow.cursoSpring.clasecuatro.controllers;

import java.util.*;
import org.itnow.cursoSpring.clasecuatro.models.*;
import org.itnow.cursoSpring.clasecuatro.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/*
 * Esta clase captura las peticiones a la API para invocar la clase service y con la clase REsponseEnt
 */
@RestController()
public class AutomovilController {

	/*
	 * Esta propiedad nos permite almacenar un objeto service.
	 * con la anotación marcamos las dependencias a esta propiedad-
	 */
	@Autowired
	AutomovilService service;
	
	/*
	 * Este método captura en el endpoint /automovil y devuelve un ResponseEntity con los datos formateados en JSON y la respuesta al navegador.
	 * La anotación es GetMapping para el métodop GET, no procesa ninguna var, tampoco el método index acepta ningún parámetro. 
	 */
	@GetMapping("/automovil")
	public ResponseEntity<List<Automovil>> index(){
		/*
		List<Automovil> misAutosFavoritos =  new ArrayList<>();
		
		misAutosFavoritos.add(new Automovil("Porsche","911",1958));
		misAutosFavoritos.add(new Automovil("Lancia","Dedra",1970));
		misAutosFavoritos.add(new Automovil("BMW","z3",1992));
		*/
		List<Automovil> misAutosFavoritos =  service.obtenerTodos();
		return ResponseEntity.ok(misAutosFavoritos);
		//return ResponseEnt"";
	}
	/*
	 * Este método devuelve un ResponsEntity con los datos de un automovil al navegador. 
	 * La anotación fórmula un endpoint q forzosamente tiene un parámentro, q se obtiene con la anotación PathVariable,  y usa el método GET de http.
	 * Si el parámetro no es pasado a clase falla
	 * Con el id(int) se instancia un objeeto automovil que devuelve la clase service.obtenerPorid con argumento el id
	 * Est métodop devuelve un objeto ResponseEntity  que se encarga de devolver los datos necearios al navegador.
	 */
	@RequestMapping(value="/automovil/{id}", method = RequestMethod.GET)
	public ResponseEntity<Automovil> newauto(@PathVariable()int id){
		/*
		return ResponseEntity.ok(new Automovil("Fiat","600",1955));
		*/
		Automovil existente = service.obtenerPorId(id);
		return ResponseEntity.ok(existente);
		}
	/*
	 * Este endpoint está anotado con PostMapping para capturar peticiones de tipos POST sobre el mismo endpoint que el anterior.
	 * Analiza el RequestBodt y realiza un cast del mismo a Automovil (entiendo que el json lo pasa como arg para instanciar un objeto de la clase automovil)
	 * Con un try/catch llama al método del objeto service.registrarNuevo y le pasa el objeto nuevo (que instancia más arriba)
	 * construye (falle o no) un objeto ResponseEntity pasandole un objeto ControllerResponse que define en tiempo de ejecución con 3 propiedades: 
	 * 		operación(text),
	 * 		success(boolean), 
	 * 		mensaje(text),
	 * 		EntidadAfectada (objeto Automovil)
	 * ; e invoca al método build.
	 * 
	 */
	@PostMapping("/automovil")
	public ResponseEntity<ControllerResponse> post(@RequestBody() Automovil nuevo){
		try {
			service.registrarNuevo(nuevo);
			return ResponseEntity.ok(ControllerResponse.builder()
					.operacion("POST Nuevo")
					.success(true)
					.mensaje("Automovil nuevo registrado")
					.entidadAfectada(nuevo)
					.build());
		} catch (Exception e){
			return ResponseEntity.internalServerError()
					.body(ControllerResponse.builder()
					.operacion("POST")
					.success(false)
					.mensaje(e.getMessage())
					.entidadAfectada(null)
					.build()); 
		}
	}
	
	/*
	 * En este método definimos un nuevo endpoint al que se le pasa un id, capturamos los métodos PUT de http, y usamos la anotación @RequestBody para construir un objeto vacio de la clase Automovil
	 * con la información que se pasa en el body que es el JSON de los datos que queremos actualizar. 
	 * Invocamos al método actualizarInfo del service para recuperar un objeto Automovil y sus datos.
	 * Con un try/catch realizamos la misma operación que en el anterior método. Construimos en tiempo de ejecución un objeto ControllerResponse definiendo sus propiedades para dar la respuesta
	 * adecuada al navegador peticionario con los datos que necesita el objeto ResponseEntity, en caso de acierto o fallo. 
	 */
	@PutMapping("/automovil/{id}")
	public ResponseEntity<ControllerResponse> put(@RequestBody() Automovil exist){
		try {
			service.actualizarInfo(exist);
			return ResponseEntity.ok(ControllerResponse
										.builder()
										.operacion("PUT Existente")
										.success(true)
										.mensaje("Automovil modificadodo")
										.entidadAfectada(exist)
										.build());
		}catch (Exception e) {
			return ResponseEntity.internalServerError().body(ControllerResponse.builder()
																.operacion("Update")
																.success(false)
																.mensaje(e.getMessage())
																.entidadAfectada(null)
																.build());
		}	
	}
	
	/*
	 *  Este método captura en el endpoint indicado el método DELETE de http, tomamos de la url la variable id y se suministra al objeto service, para invocar el método darDeBaja. 
	 *  Como en las anteriores ocasiones contruimos un obketo ControllerResponse en tiempo de ejecución suministrandolo como argumento al ResponseEntity rellenando toda la info necesaria. 
	 *  Dependiendo del resultado del try/catch se suministrará una info u otra.  
	 */
	
	@RequestMapping(value="/automovil/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ControllerResponse> delete(@PathVariable()int id){
		try {
			service.darDeBaja(id);
			return ResponseEntity.ok(ControllerResponse
					.builder()
					.operacion("DELETE Existente")
					.success(true)
					.mensaje("Automovil eliminado")
					.entidadAfectada(id)
					.build());
		}catch (Exception e){
			return ResponseEntity.internalServerError().body(ControllerResponse
					.builder()
					.operacion("DELETE")
					.success(false)
					.mensaje(e.getMessage())
					.entidadAfectada(null)
					.build());
		}
		//return ResponseEnt"";
	}
}
