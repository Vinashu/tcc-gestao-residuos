package br.uem.gestaoresiduos.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uem.gestaoresiduos.entities.Campus;
import br.uem.gestaoresiduos.entities.Laboratorio;
import br.uem.gestaoresiduos.repositories.LaboratorioRepository;
import br.uem.gestaoresiduos.repositories.LocalRepository;

@Service
@Transactional
public class LaboratorioService {

	@Autowired
	private LaboratorioRepository laboratorioRepository;
	
	@Autowired
	private LocalRepository localRepository;
	
	@Transactional(readOnly=true)
	public Page<Laboratorio> findAll(int page) {
		PageRequest pageableRequest = new PageRequest(page, 20);
		Page<Laboratorio> results = laboratorioRepository.findAll(pageableRequest);
		return results;
	}
	
	public List<Laboratorio> findByCampus(Campus campus){
		return laboratorioRepository.findByCampus(campus);
	}
	
	public List<Laboratorio> findByBloco(String bloco){
		return laboratorioRepository.findByBloco(bloco);
	}
	
	public Laboratorio findLaboratorioById(int id) {
		return (Laboratorio) localRepository.findOne(id);
		
	}
	
	public Laboratorio update(Laboratorio lab) {
		lab.setDataAtualizacao(new Date());
		return laboratorioRepository.save(lab);
	}
	
	public Laboratorio create(Laboratorio lab) {
		if (lab.getDataAtualizacao() == null) {
			lab.setDataAtualizacao(new Date());
		}
		return laboratorioRepository.save(lab);
	}
	
	public void delete(Long id) {
		laboratorioRepository.delete(id);
	}

	public List<Laboratorio> findAll() {
		return laboratorioRepository.findAll();
	}

}
