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

import br.uem.gestaoresiduos.entities.Laboratorio;
import br.uem.gestaoresiduos.services.LaboratorioService;

@Controller
@RequestMapping("api/laboratorios/")
public class LaboratorioResource {

	@Autowired
	private LaboratorioService laboratorioService;
	
	@RequestMapping(value="", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Laboratorio> create(@RequestBody Laboratorio laboratorio) {
		Laboratorio savedLab = laboratorioService.create(laboratorio);
		return new ResponseEntity<Laboratorio>(savedLab, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Laboratorio> findAll() {
		return laboratorioService.findAll();
	}
	
	@RequestMapping(value="lab/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Laboratorio> findById(@PathVariable("id") int id) {
		Laboratorio lab = laboratorioService.findLaboratorioById(id);
		return new ResponseEntity<Laboratorio>(lab, HttpStatus.OK);
	}
}
