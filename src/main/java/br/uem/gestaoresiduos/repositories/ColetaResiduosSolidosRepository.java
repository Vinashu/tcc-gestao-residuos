package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.uem.gestaoresiduos.entities.ColetaResiduosSolidos;
import br.uem.gestaoresiduos.entities.Local;
import br.uem.gestaoresiduos.entities.UnidadeCentralizadora;

public interface ColetaResiduosSolidosRepository extends JpaRepository<ColetaResiduosSolidos, Serializable>{
	
	Page<ColetaResiduosSolidos> findByDataColetaBetweenAndLocalIdAndUnidadeCentralizadoraId(Date start, Date end, Integer localId,
			Integer unidCentralizadoraId, Pageable pageRequest);

	Page<ColetaResiduosSolidos> findByUnidadeCentralizadoraId(int unidCentralizadoraId, Pageable pageableRequest);
	
}
