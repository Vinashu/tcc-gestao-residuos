package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.uem.gestaoresiduos.entities.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Serializable>{

	@Query(" select m from Material m where descricao like 'Materiais Diversos'")
	Material getMaterialdiversos();
	
	
}
	