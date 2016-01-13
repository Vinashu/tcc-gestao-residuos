package br.uem.gestaoresiduos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uem.gestaoresiduos.entities.Material;
import br.uem.gestaoresiduos.entities.TipoMaterial;
import br.uem.gestaoresiduos.repositories.MaterialRepository;

@Service
@Transactional
public class MaterialService {
	
	@Autowired
	private MaterialRepository materialRepository;
	
	public Material save(Material material){
		return materialRepository.save(material);
	}
	
	public List<Material> findByTipo(TipoMaterial tipo) {
		return materialRepository.findByTipo(tipo);
	}
	
	public List<Material> findAll() {
		return materialRepository.findAll();
	}
	
	public Material findById(int id) {
		return materialRepository.findOne(id);
	}
}
