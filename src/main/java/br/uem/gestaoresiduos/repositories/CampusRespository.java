package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uem.gestaoresiduos.entities.Campus;

@Repository
public interface CampusRespository extends JpaRepository<Campus, Serializable>{

	Campus findById(long id);

}
