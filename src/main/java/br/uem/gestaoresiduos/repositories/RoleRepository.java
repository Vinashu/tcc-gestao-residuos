package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uem.gestaoresiduos.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Serializable>
{

	Role findByRoleName(String role);

}
