package br.uem.gestaoresiduos.services;

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
	
	
}
