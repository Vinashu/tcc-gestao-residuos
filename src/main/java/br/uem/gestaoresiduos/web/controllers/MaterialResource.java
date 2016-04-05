		package br.uem.gestaoresiduos.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.uem.gestaoresiduos.entities.Material;
import br.uem.gestaoresiduos.services.MaterialService;

@Controller
@RequestMapping("api/materiais/")
public class MaterialResource {
	
	@Autowired
	private MaterialService materialService;

	@RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Material> findAll() {
		return materialService.findAll();
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Material create(@RequestBody Material material) {
		return materialService.save(material);
	}
	
	@RequestMapping(value="tiposMaterial", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String findTiposMaterial() {
		return materialService.getTiposMaterial();
	}
	
	@RequestMapping(value="material/{descricao}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Material findMaterialByDescricao(@PathVariable String descricao) {
		return materialService.findByDescricao(descricao);
	}
	
	@RequestMapping(value="material/materialDiverso", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Material findMaterialDiversos() {
		return materialService.getMaterialDiversos();
	}
	
	@RequestMapping(value="/{materialId}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Material> deleteMaterial(@PathVariable int materialId) {
		try {
			materialService.deleteMaterial(materialId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
}
