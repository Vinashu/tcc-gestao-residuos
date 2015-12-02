package br.uem.gestaoresiduos.repositories;

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
import br.uem.gestaoresiduos.entities.TipoCampus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes={AppConfig.class, PersistenceConfig.class})
public class CampusRepositoryTest {
	
	@Autowired
	private CampusRespository campusRepository;
	
	private Campus campus;

	@Before
	public void setUp() throws Exception {
		campus = new Campus();
		campus.setId(1);
		campus.setSigla("SEDE");
		campus.setNome("Campus Sede");
		campus.setTipo(TipoCampus.Sede);
		campus.setLocalizacao("Maringá");
		campus.setDataAtualizacao(new Date());
		
	}

	@Test
	public void test() {
		Campus result = campusRepository.save(campus);
		
		assertEquals(result.getNome(), "Campus Sede");
		
	}

}
