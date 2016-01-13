package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.uem.gestaoresiduos.entities.ColetaResiduosSolidos;

public interface ColetaResiduosSolidosRepository extends JpaRepository<ColetaResiduosSolidos, Serializable>{
	
	Page<ColetaResiduosSolidos> findByDataColetaBetweenAndLocalIdAndUnidadeCentralizadoraId(Date start, Date end, Integer localId,
			Integer unidCentralizadoraId, Pageable pageRequest);

	Page<ColetaResiduosSolidos> findByDataColetaBetweenAndUnidadeCentralizadoraId(Date start, Date end,
			int unidCentralizadoraId, Pageable pageableRequest);

	Page<ColetaResiduosSolidos> findByLocalIdAndUnidadeCentralizadoraId(int localId, int unidCentralizadoraId, Pageable pageableRequest);
	
	Page<ColetaResiduosSolidos> findByUnidadeCentralizadoraId(int unidCentralizadoraId, Pageable pageableRequest);
}
