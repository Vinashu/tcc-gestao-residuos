package br.uem.gestaoresiduos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uem.gestaoresiduos.entities.Campus;
import br.uem.gestaoresiduos.entities.Laboratorio;
import br.uem.gestaoresiduos.repositories.LaboratorioRepository;

@Service
@Transactional
public class LaboratorioService {

	@Autowired
	private LaboratorioRepository laboratorioRepository;
	
	public List<Laboratorio> findAll() {
		return laboratorioRepository.findAll();
	}
	
	public List<Laboratorio> findByCampus(Campus campus){
		return laboratorioRepository.findByCampus(campus);
	}
	
	public List<Laboratorio> findByBloco(String bloco){
		return laboratorioRepository.findByBloco(bloco);
	}
	
	public Laboratorio findLaboratorioById(Long id) {
		return laboratorioRepository.findOne(id);
		
	}
	
	public Laboratorio update(Laboratorio lab) {
		return laboratorioRepository.save(lab);
	}
	
	public Laboratorio create(Laboratorio lab) {
		return laboratorioRepository.save(lab);
	}
	
	public void delete(Long id) {
		laboratorioRepository.delete(id);
	}

}
