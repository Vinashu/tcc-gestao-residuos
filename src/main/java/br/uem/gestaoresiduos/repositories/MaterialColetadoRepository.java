package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.uem.gestaoresiduos.entities.Local;
import br.uem.gestaoresiduos.entities.MaterialColetado;

public interface MaterialColetadoRepository extends JpaRepository<MaterialColetado, Serializable>{
	
	
	}
