package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uem.gestaoresiduos.entities.ColetaResiduosSolidos;
import br.uem.gestaoresiduos.entities.Local;
import br.uem.gestaoresiduos.entities.UnidadeCentralizadora;

public interface ColetaResiduosSolidosRepository extends JpaRepository<ColetaResiduosSolidos, Serializable>{
	
	List<ColetaResiduosSolidos> findByDataColetaBetweenAndLocalAndUnidadeCentralizadora(Date start, Date end, Local local,
			UnidadeCentralizadora unidCentralizadora);
	
}
