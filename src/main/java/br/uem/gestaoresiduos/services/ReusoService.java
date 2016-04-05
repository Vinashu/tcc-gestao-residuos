package br.uem.gestaoresiduos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

import br.uem.gestaoresiduos.entities.Material;
import br.uem.gestaoresiduos.entities.Reuso;
import br.uem.gestaoresiduos.entities.StatusReuso;
import br.uem.gestaoresiduos.repositories.ReusoRepository;

@Service
@Transactional
public class ReusoService {

	@Autowired
	private ReusoRepository reusoRepository;

	public Reuso save(Reuso reuso) {
		return reusoRepository.save(reuso);
	}

	public void delete(int id) {
		reusoRepository.delete(id);
	}

	public Reuso findByMaterialId(int materialId) {
		return reusoRepository.findByMaterialId(materialId);
	}

	public void deleteByMaterialId(int id) {
		reusoRepository.deleteByMaterialId(id);
	}

	public Page<Reuso> findAll(int page) {
		PageRequest pageableRequest = new PageRequest(page, 20);
		return reusoRepository.findByStatus(StatusReuso.Disponível,pageableRequest);
	}

	public Page<Reuso> findByCampusMaterial(JsonNode search, Integer page) {
		PageRequest pageableRequest = new PageRequest(page, 20);
		int campusId, materialId;
		Page<Reuso> results = null;
		
		campusId = search.get("campusId").asInt();
		materialId = search.get("materialId").asInt();
		
		if (campusId > 0) {
			results = materialId > 0 
				 ? reusoRepository.findByCampusIdAndMaterialColetadoMaterialIdAndStatus(campusId, materialId, StatusReuso.Disponível, pageableRequest)
				 : reusoRepository.findByCampusIdAndStatus(campusId, StatusReuso.Disponível, pageableRequest);
		}else{
			results = materialId > 0
				? reusoRepository.findByMaterialColetadoMaterialIdAndStatus(materialId, StatusReuso.Disponível, pageableRequest)
				: reusoRepository.findByStatus(StatusReuso.Disponível,pageableRequest);
		}
		return results;

	}

	public List<Material> findMateriaisDisp() {
		return reusoRepository.findMateriaisDisp();
	}

}
