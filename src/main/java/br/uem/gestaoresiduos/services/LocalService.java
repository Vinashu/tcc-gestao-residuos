package br.uem.gestaoresiduos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uem.gestaoresiduos.entities.Local;
import br.uem.gestaoresiduos.repositories.LocalRepository;

@Service
public class LocalService {
	
	@Autowired
	private LocalRepository localRepository;
	
	public List<Local> findAll(){
		return localRepository.findAll();
	}

	public List<Local> findNotLab() {
		return localRepository.findByTipoDifLab();
	}
	
}
