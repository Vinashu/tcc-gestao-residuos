package br.uem.gestaoresiduos.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;

import br.uem.gestaoresiduos.entities.Material;
import br.uem.gestaoresiduos.entities.Reuso;
import br.uem.gestaoresiduos.services.ReusoService;

@Controller
@RequestMapping("api/reusos/")
public class ReusoResource {

	@Autowired
	private ReusoService reusoService;
	
	@RequestMapping(value="", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Reuso> create(@RequestBody Reuso reuso) {
		Reuso savedReuso = reusoService.save(reuso);
		return new ResponseEntity<Reuso>(savedReuso, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Reuso> delete(@PathVariable("id") int id) {
		try{
			reusoService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Reuso>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="findMateriais", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Material> findMateriaisDisp() {
		return reusoService.findMateriaisDisp();
	}
	
	@RequestMapping(value="reuso/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Reuso> deleteByMaterial(@PathVariable("id") int id) {
			reusoService.deleteByMaterialId(id);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="reuso/{materialId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Reuso> findById(@PathVariable("materialId") int materialId) {
		Reuso reuso = reusoService.findByMaterialId(materialId);
		return new ResponseEntity<Reuso>(reuso, HttpStatus.OK);
	}
	
	@RequestMapping(value="findAll/{page}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<Reuso> findAll(@PathVariable("page") int page) {
		return reusoService.findAll(page);
	}
	
	@RequestMapping(value="find/{page}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<Reuso> find(@PathVariable Integer page, @RequestBody JsonNode search) {
		return reusoService.findByCampusMaterial(search, page);
	}
	
}
