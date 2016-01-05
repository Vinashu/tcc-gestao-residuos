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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;

import br.uem.gestaoresiduos.entities.ColetaResiduosSolidos;
import br.uem.gestaoresiduos.entities.Local;
import br.uem.gestaoresiduos.entities.UnidadeCentralizadora;
import br.uem.gestaoresiduos.services.ColetaResiduosSolidosService;

@Controller
@RequestMapping("api/coletaResiduosSolidos/")
public class ColetaResiduosSolidosResource {

	@Autowired
	private ColetaResiduosSolidosService coletaResiduosSolidosService;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ColetaResiduosSolidos> create(@RequestBody ColetaResiduosSolidos coletaResiduosSolidos) {
		ColetaResiduosSolidos savedColeta = coletaResiduosSolidosService.create(coletaResiduosSolidos);
		return new ResponseEntity<ColetaResiduosSolidos>(savedColeta, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="find/{page}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<ColetaResiduosSolidos> find(@PathVariable Integer page, @RequestBody JsonNode search) {
		return coletaResiduosSolidosService.findByMesAnoLocalUnidCentralizadora(search, page);
	}
	
	@RequestMapping(value = "findAll/{page}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<ColetaResiduosSolidos> findAll(@PathVariable Integer page, @RequestBody JsonNode unidId) {
		return coletaResiduosSolidosService.findAll(unidId, page);
	}

	@RequestMapping(value = "coleta/{coletaId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ColetaResiduosSolidos> findById(@PathVariable int id) {
		ColetaResiduosSolidos coleta = coletaResiduosSolidosService.findById(id);
		return new ResponseEntity<ColetaResiduosSolidos>(coleta, HttpStatus.OK);
	}

}
