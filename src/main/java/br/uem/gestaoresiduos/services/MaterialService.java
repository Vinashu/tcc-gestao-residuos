package br.uem.gestaoresiduos.services;

import java.util.Arrays;
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
	
	public List<Material> findAll() {
		return materialRepository.findAll();
	}
	
	public Material findById(int id) {
		return materialRepository.findOne(id);
	}
	
	public Material findByDescricao(String descricao){
		return materialRepository.findOne(descricao);
	}

	public String getTiposMaterial() {
		StringBuffer tiposMaterial = new StringBuffer();
		List<TipoMaterial> tipos = Arrays.asList(TipoMaterial.values());
		
		tiposMaterial.append("[ ");
		tipos.forEach(t -> {
			tiposMaterial.append("{ \"tipo\" : \"" + t + "\" , \"descricao\" : \"" + t.getDescricao() + "\" },");
		});
		tiposMaterial.deleteCharAt(tiposMaterial.length() - 1);
		tiposMaterial.append(" ]");
		
		return tiposMaterial.toString();
	}

	public void deleteMaterial(int materialId){
		materialRepository.delete(materialId);
	}

	public Material getMaterialDiversos() {
		return materialRepository.getMaterialdiversos();
	}
}
