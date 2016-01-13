package br.uem.gestaoresiduos.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import br.uem.gestaoresiduos.config.AppConfig;
import br.uem.gestaoresiduos.config.PersistenceConfig;
import br.uem.gestaoresiduos.entities.Material;
import br.uem.gestaoresiduos.entities.TipoMaterial;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes={AppConfig.class, PersistenceConfig.class})
public class MaterialServiceTest {

	public Material material1;
	public Material material2;
	public Material material3;
	public Material material4;
	
	@Autowired
	private MaterialService materialService;
	
	@Before
	public void setUp() throws Exception {
		material1 = new Material("Alcool contaminado", TipoMaterial.AMBOS);
		material2 = new Material("Reagentes sólidos vencidos", TipoMaterial.QUIMICO);
		material3 = new Material("Embalagens de agrotoxicos", TipoMaterial.QUIMICO);
		material4 = new Material("Litros vazios", TipoMaterial.AMBOS);
	}

	@Test
	public void testSave() {
		Material result = materialService.save(material1);
		assertEquals(result.getDescricao(), material1.getDescricao());
		
		result = materialService.save(material2);
		assertEquals(result.getDescricao(), material2.getDescricao());
		
		result = materialService.save(material3);
		assertEquals(result.getDescricao(), material3.getDescricao());
	
		result = materialService.save(material3);
		assertEquals(result.getDescricao(), material3.getDescricao());
	
	}

}
