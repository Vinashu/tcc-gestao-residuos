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

import br.uem.gestaoresiduos.entities.ColetaResiduosSolidos;
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

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ColetaResiduosSolidos> findAll() {
		return coletaResiduosSolidosService.findAll();
	}

	@RequestMapping(value = "coleta/{coletaId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ColetaResiduosSolidos> findById(@PathVariable int id) {
		ColetaResiduosSolidos coleta = coletaResiduosSolidosService.findById(id);
		return new ResponseEntity<ColetaResiduosSolidos>(coleta, HttpStatus.OK);
	}

}
