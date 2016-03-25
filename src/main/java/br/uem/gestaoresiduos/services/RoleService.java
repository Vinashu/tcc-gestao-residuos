package br.uem.gestaoresiduos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uem.gestaoresiduos.entities.Role;
import br.uem.gestaoresiduos.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role getById(int id) {
		return roleRepository.getOne(id);
	}
	
	public List<Role> list(){
		return roleRepository.findAll();
	}

	public Role findByRoleName(String role) {
		return roleRepository.findByRoleName(role);
	}
	
}
