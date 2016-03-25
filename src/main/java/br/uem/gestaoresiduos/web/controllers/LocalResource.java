package br.uem.gestaoresiduos.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.uem.gestaoresiduos.entities.Local;
import br.uem.gestaoresiduos.entities.LocalColeta;
import br.uem.gestaoresiduos.services.LocalService;

@Controller
@RequestMapping("api/locais/")
public class LocalResource {

	@Autowired
	private LocalService localService;
	
	@RequestMapping(path="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Local> findAll() {
		return localService.findAll();
	}
	
	@RequestMapping(path="locaisColetaSolidos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Local> findLocaisColetaSolidos() {
		return localService.findLocaisColetaSolidos();
	}
	
	@RequestMapping(path="locaisColetaServicosSaude", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Local> findLocaisColetaServicosSaude() {
		return localService.findLocaisColetaServicosSaude();
	}
	
	@RequestMapping(path="notLab", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Local> findNotLab() {
		return localService.findNotLab();
	}
	
	@RequestMapping(path="locaisColeta", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LocalColeta> findLocaisColeta() {
		return localService.findLocaisColeta();
	}
	
	@RequestMapping(value="localColeta", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<LocalColeta> create(@RequestBody LocalColeta localColeta) {
		LocalColeta savedLocal = localService.createLocalColeta(localColeta);
		return new ResponseEntity<LocalColeta>(savedLocal, HttpStatus.CREATED);
	}
}
