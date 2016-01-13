package br.uem.gestaoresiduos.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ArrayNode;

import br.uem.gestaoresiduos.services.UndiadeMedidaService;

@Controller
@RequestMapping("api/unidadeMedida/")
public class UnidadeMedidaResource {
	
	@Autowired
	private UndiadeMedidaService unidadeMedidaService;
	
	@RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ArrayNode listUnidadesMedida() {
		return unidadeMedidaService.listUnidadesMedida();
	}
	
}
