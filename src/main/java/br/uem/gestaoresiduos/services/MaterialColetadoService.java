package br.uem.gestaoresiduos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uem.gestaoresiduos.entities.MaterialColetado;
import br.uem.gestaoresiduos.repositories.MaterialColetadoRepository;

@Service
public class MaterialColetadoService {
	
	@Autowired
	private MaterialColetadoRepository materialColetadoRepository;
	
	public MaterialColetado updateMaterialColetado(MaterialColetado materialColetado){
		return materialColetadoRepository.save(materialColetado);
	}
	
	
	
}
