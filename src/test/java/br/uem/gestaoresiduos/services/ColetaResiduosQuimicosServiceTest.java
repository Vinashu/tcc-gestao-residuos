package br.uem.gestaoresiduos.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import br.uem.gestaoresiduos.config.AppConfig;
import br.uem.gestaoresiduos.config.PersistenceConfig;
import br.uem.gestaoresiduos.entities.ColetaResiduosQuimicos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes={AppConfig.class, PersistenceConfig.class})
public class ColetaResiduosQuimicosServiceTest {

	@Autowired
	private ColetaResiduosQuimicosService coletaResiduosQuimicosService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		int id = 59;
		
		ColetaResiduosQuimicos result = coletaResiduosQuimicosService.findById(id);
		
		assertEquals(result.getId(), id);
	}

}
