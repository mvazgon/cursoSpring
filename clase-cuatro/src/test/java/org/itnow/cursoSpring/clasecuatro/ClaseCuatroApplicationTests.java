package org.itnow.cursoSpring.clasecuatro;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.itnow.cursoSpring.clasecuatro.controllers.AutomovilController;
import org.itnow.cursoSpring.clasecuatro.models.Automovil;
import org.itnow.cursoSpring.clasecuatro.repository.AutomovilMockRespository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClaseCuatroApplicationTests {

	@Autowired
	AutomovilController controller;
	
	@Test
	void testController() {
		List<Automovil> automoviles = controller.index().getBody();
		int totalAntes = automoviles.size();
		
		Automovil auto = new Automovil();
		auto.setId(Automovil.nextId());
		auto.setMarca("Seat");
		auto.setModelo("Ibiza");
		auto.setAño(1982);
		controller.post(auto);
		
		int totalDespues = automoviles.size();
		
		assertEquals(totalDespues,totalAntes+1);
	}
	
	@Test
	void contextLoads() {
		
	}

}
