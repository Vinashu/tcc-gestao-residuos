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

import br.uem.gestaoresiduos.entities.TiposResiduos;
import br.uem.gestaoresiduos.entities.UnidadeCentralizadora;
import br.uem.gestaoresiduos.entities.User;
import br.uem.gestaoresiduos.services.UnidadeCentralidoraService;


@Controller
@RequestMapping("api/unidCentralizadoras/")
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
	
	@RequestMapping(value="{tipoResiduos}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UnidadeCentralizadora> findByTipoResiduos(@PathVariable("tipoResiduos") TiposResiduos tiposResiduos) {
		return unidadeCentralidoraService.findByTipoResiduos(tiposResiduos);
	}
	
	@RequestMapping(value="{tipoResiduos}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UnidadeCentralizadora> findByTipoResiduosAndUser(@PathVariable("tipoResiduos") TiposResiduos tiposResiduos, @RequestBody User user) {
		return unidadeCentralidoraService.findByTipoResiduosAndUser(tiposResiduos, user);
	}
	
	@RequestMapping(value="unid/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<UnidadeCentralizadora> findById(@PathVariable("id") int id) {
		UnidadeCentralizadora unid = unidadeCentralidoraService.findById(id);
		return new ResponseEntity<UnidadeCentralizadora>(unid, HttpStatus.OK);
	}
	
	@RequestMapping(value="unid/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<UnidadeCentralizadora> deleteById(@PathVariable("id") int id) {
		try{
			unidadeCentralidoraService.deleteById(id);
			return new ResponseEntity<UnidadeCentralizadora>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<UnidadeCentralizadora>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
