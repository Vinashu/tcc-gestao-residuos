package br.uem.gestaoresiduos.services;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.uem.gestaoresiduos.config.AppConfig;
import br.uem.gestaoresiduos.config.PersistenceConfig;
import br.uem.gestaoresiduos.entities.ColetaResiduosSolidos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes={AppConfig.class, PersistenceConfig.class})
public class ColetaResiduosSolidosServiceTest {

	@Autowired
	private ColetaResiduosSolidosService coletaResiduosSolidosService;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testfindByMesAnoLocalUnidCentralizadora() throws JsonParseException, IOException {
		int page = 0;
		String parametros = "{ \"mes\" : 12 , \"ano\" : 2015 , \"localId\" : 1, \"unidCentralizadoraId\" : 4 }";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode search = mapper.readValue(parametros, JsonNode.class);
		
		Page<ColetaResiduosSolidos> result = coletaResiduosSolidosService.findByMesAnoLocalUnidCentralizadora(search, page);
		assertEquals(result.getNumberOfElements(), 4);
	}

}
