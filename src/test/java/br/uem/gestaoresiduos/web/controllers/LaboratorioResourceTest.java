/**
 * 
 */
package br.uem.gestaoresiduos.web.controllers;

import static org.junit.Assert.fail;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;

import br.uem.gestaoresiduos.config.AppConfig;
import br.uem.gestaoresiduos.config.PersistenceConfig;

/**
 * @author root
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes={AppConfig.class, PersistenceConfig.class})
public class LaboratorioResourceTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	            MediaType.APPLICATION_JSON.getSubtype(),
	            Charset.forName("utf8"));

    private MockMvc mockMvc;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link br.uem.gestaoresiduos.web.controllers.LaboratorioResource#create(br.uem.gestaoresiduos.entities.Laboratorio)}.
	 */
	@Test
	public void testCreate() {
		//fail("Not yet implemented");
	}

}
