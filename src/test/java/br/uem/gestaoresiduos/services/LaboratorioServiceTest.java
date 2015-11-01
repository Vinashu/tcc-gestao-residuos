/**
 * 
 */
package br.uem.gestaoresiduos.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import br.uem.gestaoresiduos.repositories.CampusRespository;

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
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		campus = new Campus();
		campus.setId(1);
		campus.setLocalizacao("Maringá");
		campus.setNome("Campus principal");
		campus.setTipo(TipoCampus.Sede);
		
		laboratorio = new Laboratorio();
		laboratorio.setBloco("E90");
		laboratorio.setCampus(campus);
		laboratorio.setDataAtualizacao(new Date());
		laboratorio.setResponsavel("Prof Delano Caraio");
		laboratorio.setSala("001");
		laboratorio.setTipoAtividade("Atividades Diversas");
		laboratorio.setTipoResiduos(TiposResiduos.QUIMICO);
		
	}

	@Test
	public void test() {
		Laboratorio result = laboratorioService.create(laboratorio);
		assertEquals(result.getSala(), "001");
	}

}
