package br.uem.gestaoresiduos.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.uem.gestaoresiduos.entities.Role;
import br.uem.gestaoresiduos.services.RoleService;

@Controller
@RequestMapping("api/role/")
public class RoleResource {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Role> findAll() {
		List<Role> roles = roleService.list();
		return roles;
	}
}
