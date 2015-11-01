package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uem.gestaoresiduos.entities.Campus;
import br.uem.gestaoresiduos.entities.Laboratorio;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Serializable>{

	List<Laboratorio> findByCampus(Campus campus);

	List<Laboratorio> findByBloco(String bloco);

}
