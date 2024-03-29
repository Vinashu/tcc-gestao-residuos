package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.uem.gestaoresiduos.entities.Campus;
import br.uem.gestaoresiduos.entities.Laboratorio;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Serializable>{
	
	Page<Laboratorio> findAll(Pageable pageRequest);

	List<Laboratorio> findByCampus(Campus campus);

	List<Laboratorio> findByBloco(String bloco);

	void deleteById(int id);

}
