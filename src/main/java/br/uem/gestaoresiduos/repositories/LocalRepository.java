package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.uem.gestaoresiduos.entities.Local;

public interface LocalRepository extends JpaRepository<Local, Serializable>{
	
	@Query("select l from Local l where local_tipo = 'campus'")
	public List<Local> findAllCampus();
}
