package br.uem.gestaoresiduos.repositories;

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
import br.uem.gestaoresiduos.entities.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes={AppConfig.class, PersistenceConfig.class})
public class RoleRepositoryTest {

	@Autowired
	private RoleRepository roleRepository;
	
	Role adminRole;
	Role unidCentralRole;
	Role pesquisador;
	
	@Before
	public void setUp() throws Exception {
		adminRole = new Role(1, "GESTORRESIDUOS");
		unidCentralRole = new Role(2, "TECNICOCOLETA");
		pesquisador = new Role(3, "PESQUISADOR");
	}
	
	@Test
	public void testSaveIterableOfS() {
		Role savedRoleAdmin = roleRepository.save(adminRole);
		Role savedRoleUnidCentral = roleRepository.save(unidCentralRole);
		roleRepository.save(pesquisador);
		
		assertEquals("GESTORRESIDUOS", savedRoleAdmin.getRoleName());
		assertEquals("TECNICOCOLETA", savedRoleUnidCentral.getRoleName());
	}

	@Test
	public void testGetOne() {
		assertEquals("GESTORRESIDUOS", roleRepository.getOne(1));
	}

}
