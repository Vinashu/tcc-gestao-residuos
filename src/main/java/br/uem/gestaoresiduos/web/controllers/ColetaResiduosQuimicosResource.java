package br.uem.gestaoresiduos.web.controllers;

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

import br.uem.gestaoresiduos.entities.ColetaResiduosQuimicos;
import br.uem.gestaoresiduos.services.ColetaResiduosQuimicosService;

@Controller
@RequestMapping("api/coletaResiduosQuimicos/")
public class ColetaResiduosQuimicosResource {

	@Autowired
	private ColetaResiduosQuimicosService coletaResiduosQuimicosService;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ColetaResiduosQuimicos> create(@RequestBody ColetaResiduosQuimicos coletaResiduosQuimicos) {
		ColetaResiduosQuimicos savedColeta = coletaResiduosQuimicosService.create(coletaResiduosQuimicos);
		return new ResponseEntity<ColetaResiduosQuimicos>(savedColeta, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="find/{page}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<ColetaResiduosQuimicos> find(@PathVariable Integer page, @RequestBody JsonNode search) {
		return coletaResiduosQuimicosService.findByMesAnoLocalUnidCentralizadora(search, page);
	}
	
	@RequestMapping(value = "findAll/{page}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<ColetaResiduosQuimicos> findAll(@PathVariable Integer page, @RequestBody JsonNode unidId) {
		return coletaResiduosQuimicosService.findAll(unidId, page);
	}

	@RequestMapping(value = "coleta/{coletaId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ColetaResiduosQuimicos> findById(@PathVariable int id) {
		ColetaResiduosQuimicos coleta = coletaResiduosQuimicosService.findById(id);
		return new ResponseEntity<ColetaResiduosQuimicos>(coleta, HttpStatus.OK);
	}

}
