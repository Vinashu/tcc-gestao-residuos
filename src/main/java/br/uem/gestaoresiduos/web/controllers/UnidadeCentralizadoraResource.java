package br.uem.gestaoresiduos.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.uem.gestaoresiduos.entities.UnidadeCentralizadora;
import br.uem.gestaoresiduos.services.UnidadeCentralidoraService;

public class UnidadeCentralizadoraResource {

	@Autowired
	private UnidadeCentralidoraService unidadeCentralidoraService;
	
	@RequestMapping(value="", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<UnidadeCentralizadora> create(@RequestBody UnidadeCentralizadora unidadeCentralizadora) {
		UnidadeCentralizadora result = unidadeCentralidoraService.create(unidadeCentralizadora);
		return new ResponseEntity<UnidadeCentralizadora>(result, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UnidadeCentralizadora> findAll() {
		return unidadeCentralidoraService.findAll();
	}
}