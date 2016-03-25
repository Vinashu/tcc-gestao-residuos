package br.uem.gestaoresiduos.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.uem.gestaoresiduos.services.TiposResiduosService;

@Controller
@RequestMapping("api/tiposResiduos/")
public class TiposResiduosResouce {
	
	@Autowired
	private TiposResiduosService tiposResiduosService;

	@RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String findAll() {
		return tiposResiduosService.findAll();
	}
	
}
