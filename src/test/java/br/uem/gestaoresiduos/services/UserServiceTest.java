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
import br.uem.gestaoresiduos.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes={AppConfig.class, PersistenceConfig.class})
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	private User user;
	
	@Before
	public void setUp() throws Exception {
		user = new User(1, "victor", "victort@asd.ad", "sfd", null);
	}

	@Test
	public void testCreate() {
		User result = userService.create(user);
		
		assertEquals("victor", result.getName());
		
	}

}
