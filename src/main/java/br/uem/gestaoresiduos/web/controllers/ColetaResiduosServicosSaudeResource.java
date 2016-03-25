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

import br.uem.gestaoresiduos.entities.ColetaResiduosHospitalares;
import br.uem.gestaoresiduos.services.ColetaResiduosHospitalaresService;

@Controller
@RequestMapping("api/coletaResiduosServicosSaude/")
public class ColetaResiduosServicosSaudeResource {

	@Autowired
	private ColetaResiduosHospitalaresService coletaResiduosHospitalaresService;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ColetaResiduosHospitalares> create(@RequestBody ColetaResiduosHospitalares coletaResiduosHospitalares) {
		ColetaResiduosHospitalares savedColeta = coletaResiduosHospitalaresService.create(coletaResiduosHospitalares);
		return new ResponseEntity<ColetaResiduosHospitalares>(savedColeta, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="find/{page}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<ColetaResiduosHospitalares> find(@PathVariable Integer page, @RequestBody JsonNode search) {
		return coletaResiduosHospitalaresService.findByMesAnoLocalUnidCentralizadora(search, page);
	}
	
	@RequestMapping(value = "findAll/{page}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<ColetaResiduosHospitalares> findAll(@PathVariable Integer page, @RequestBody JsonNode unidId) {
		return coletaResiduosHospitalaresService.findAll(unidId, page);
	}

	@RequestMapping(value = "coleta/{coletaId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ColetaResiduosHospitalares> findById(@PathVariable int id) {
		ColetaResiduosHospitalares coleta = coletaResiduosHospitalaresService.findById(id);
		return new ResponseEntity<ColetaResiduosHospitalares>(coleta, HttpStatus.OK);
	}
	
	@RequestMapping(value = "coleta/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ColetaResiduosHospitalares> deleteById(@PathVariable int id) {
		if (coletaResiduosHospitalaresService.deleteById(id)) {
			return new ResponseEntity<ColetaResiduosHospitalares>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
