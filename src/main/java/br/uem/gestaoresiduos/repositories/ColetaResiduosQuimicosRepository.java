package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.uem.gestaoresiduos.entities.ColetaResiduosQuimicos;

public interface ColetaResiduosQuimicosRepository extends JpaRepository<ColetaResiduosQuimicos, Serializable>{

	Page<ColetaResiduosQuimicos> findByUnidadeCentralizadoraId(int unidCentralizadoraId, Pageable pageableRequest);

	Page<ColetaResiduosQuimicos> findByDataColetaBetweenAndLaboratorioIdAndUnidadeCentralizadoraId(Date start, Date end, int localId,
			int unidCentralizadoraId, Pageable pageableRequest);

	Page<ColetaResiduosQuimicos> findByDataColetaBetweenAndUnidadeCentralizadoraId(Date start, Date end,
			int unidCentralizadoraId, Pageable pageableRequest);

	Page<ColetaResiduosQuimicos> findByLaboratorioIdAndUnidadeCentralizadoraId(int localId, int unidCentralizadoraId,
			Pageable pageableRequest);
	
	ColetaResiduosQuimicos findById(int id);

}
