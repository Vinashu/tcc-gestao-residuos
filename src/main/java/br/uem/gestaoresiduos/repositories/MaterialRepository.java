package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uem.gestaoresiduos.entities.Material;
import br.uem.gestaoresiduos.entities.TipoMaterial;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Serializable>{
	
	List<Material> findByTipo(TipoMaterial tipo);
	
}
