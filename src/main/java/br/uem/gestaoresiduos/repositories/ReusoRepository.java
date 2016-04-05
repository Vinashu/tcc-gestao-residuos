package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.uem.gestaoresiduos.entities.Material;
import br.uem.gestaoresiduos.entities.Reuso;
import br.uem.gestaoresiduos.entities.StatusReuso;

public interface ReusoRepository extends JpaRepository<Reuso, Serializable>{

	@Query("select r from Reuso r where r.materialColetado.id = ?1" )
	Reuso findByMaterialId(int materialId);

	@Modifying
	@Query(value = "delete from reuso where material_coletado_id = ?1", nativeQuery=true)
	void deleteByMaterialId(int id);

	Page<Reuso> findByCampusIdAndMaterialColetadoMaterialIdAndStatus(int campusId, int materialId, StatusReuso status, Pageable pageableRequest);

	@Query(value="select mc.material_id as id, m.descricao as descricao from material_coletado mc "
			+ "join reuso r on mc.material_coletado_id = r.material_coletado_id "
			+ "join material m on m.material_id = mc.material_id where r.status = 'Disponível' group by m.material_id", nativeQuery=true)
	List<Material> findMateriaisDisp();

	Page<Reuso> findByCampusIdAndStatus(int campusId, StatusReuso disponível, Pageable pageableRequest);

	Page<Reuso> findByMaterialColetadoMaterialIdAndStatus(int materialId, StatusReuso disponível,
			Pageable pageableRequest);

	Page<Reuso> findByStatus(StatusReuso disponível, Pageable pageableRequest);
	
	
	}
