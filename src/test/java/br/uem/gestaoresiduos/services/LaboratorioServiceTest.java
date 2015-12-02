/**
 * 
 */
package br.uem.gestaoresiduos.services;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import br.uem.gestaoresiduos.config.AppConfig;
import br.uem.gestaoresiduos.config.PersistenceConfig;
import br.uem.gestaoresiduos.entities.Campus;
import br.uem.gestaoresiduos.entities.Laboratorio;
import br.uem.gestaoresiduos.entities.TipoCampus;
import br.uem.gestaoresiduos.entities.TiposResiduos;

/**
 * @author root
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes={AppConfig.class, PersistenceConfig.class})
public class LaboratorioServiceTest {
	
	private Laboratorio laboratorio;
	private Campus campus;
	
	@Autowired
	private LaboratorioService laboratorioService;
	
	@Autowired
	private CampusService campusService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Integer id = 1;
		campus = campusService.findById(id);
		
		laboratorio = new Laboratorio();
		laboratorio.setBloco("E90");
		laboratorio.setCampus(campus);
		laboratorio.setNome("Laboratório de Química Orgânica");
		laboratorio.setSigla("LQO");
		laboratorio.setDataAtualizacao(new Date());
		laboratorio.setResponsavel("Prof Delano Caraio");
		laboratorio.setSala("001");
		laboratorio.setTelefone("3011-2010");
		laboratorio.setEmail("lqo@uem.br");
		laboratorio.setTipoAtividade("Atividades Diversas");
		laboratorio.setTipoResiduos(TiposResiduos.QUIMICO);
		
	}

	@Test
	public void test() {
		Laboratorio result = laboratorioService.create(laboratorio);
		assertEquals(result.getSala(), "001");
	}

}
