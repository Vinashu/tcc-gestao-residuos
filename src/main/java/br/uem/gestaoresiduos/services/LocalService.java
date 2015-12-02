package br.uem.gestaoresiduos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uem.gestaoresiduos.entities.Local;
import br.uem.gestaoresiduos.repositories.LocalRespository;

@Service
public class LocalService {
	
	@Autowired
	private LocalRespository localRespository;
	
	public List<Local> findAll(){
		return localRespository.findAll();
	}
}
