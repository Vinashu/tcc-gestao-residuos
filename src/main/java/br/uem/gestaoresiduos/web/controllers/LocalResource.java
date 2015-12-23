package br.uem.gestaoresiduos.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.uem.gestaoresiduos.entities.Local;
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
	
}
