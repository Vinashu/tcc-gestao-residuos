package br.uem.gestaoresiduos.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uem.gestaoresiduos.entities.Local;
import br.uem.gestaoresiduos.entities.LocalColeta;
import br.uem.gestaoresiduos.repositories.LocalColetaRepository;
import br.uem.gestaoresiduos.repositories.LocalRepository;

@Service
public class LocalService {
	
	@Autowired
	private LocalRepository localRepository;
	
	@Autowired
	private LocalColetaRepository localColetaRepository;
	
	public List<Local> findAll(){
		return localRepository.findAll();
	}

	public List<Local> findNotLab() {
		return localRepository.findByTipoDifLab();
	}

	public List<Local> findLocaisColetaSolidos() {
		return localRepository.findByTipoColetaSolidos();
	}
	
	public List<Local> findLocaisColetaServicosSaude() {
		return localRepository.findByTipoColetaServicosSaude();
	}
	
	public LocalColeta createLocalColeta(LocalColeta coleta) {
		if (coleta.getDataAtualizacao() == null) {
			coleta.setDataAtualizacao(new Date());
		}
		return localColetaRepository.save(coleta);
	}

	public List<LocalColeta> findLocaisColeta() {
		return localColetaRepository.findAll();
	}

	
	
}
