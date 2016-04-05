package br.uem.gestaoresiduos.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.uem.gestaoresiduos.entities.MaterialColetado;
import br.uem.gestaoresiduos.services.MaterialColetadoService;

@Controller
@RequestMapping("api/materiaisColetados/")
public class MaterialColetadoResource {

	@Autowired
	private MaterialColetadoService materialColetadoService;
	
	@RequestMapping(value="", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<MaterialColetado> update(@RequestBody MaterialColetado materialColetado) {
		MaterialColetado updatedMaterial = materialColetadoService.updateMaterialColetado(materialColetado);
		return new ResponseEntity<MaterialColetado>(updatedMaterial, HttpStatus.CREATED);
	}
}
