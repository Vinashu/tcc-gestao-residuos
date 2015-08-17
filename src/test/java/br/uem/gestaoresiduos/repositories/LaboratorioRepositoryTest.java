package br.uem.gestaoresiduos.repositories;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uem.gestaoresiduos.entities.Campus;
import br.uem.gestaoresiduos.entities.Laboratorio;

public class LaboratorioRepositoryTest {
	
	private LaboratorioRepository laboratorioRespository; 
	

	@Before
	public void setUp() throws Exception {
		Laboratorio lab = new Laboratorio();
		Campus campus = new Campus();
	}

	@Test
	public void testFindAllIterableOfID() {
//		fail("Not yet implemented");
	}

	@Test
	public void testSaveIterableOfS() {
//		fail("Not yet implemented");
	}

}
