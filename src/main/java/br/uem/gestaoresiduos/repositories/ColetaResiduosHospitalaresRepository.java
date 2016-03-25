package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.uem.gestaoresiduos.entities.ColetaResiduosHospitalares;

public interface ColetaResiduosHospitalaresRepository extends JpaRepository<ColetaResiduosHospitalares, Serializable>{
	
	Page<ColetaResiduosHospitalares> findByDataColetaBetweenAndLocalIdAndUnidadeCentralizadoraId(Date start, Date end, Integer localId,
			Integer unidCentralizadoraId, Pageable pageRequest);

	Page<ColetaResiduosHospitalares> findByDataColetaBetweenAndUnidadeCentralizadoraId(Date start, Date end,
			int unidCentralizadoraId, Pageable pageableRequest);

	Page<ColetaResiduosHospitalares> findByLocalIdAndUnidadeCentralizadoraId(int localId, int unidCentralizadoraId, Pageable pageableRequest);
	
	Page<ColetaResiduosHospitalares> findByUnidadeCentralizadoraId(int unidCentralizadoraId, Pageable pageableRequest);
}
