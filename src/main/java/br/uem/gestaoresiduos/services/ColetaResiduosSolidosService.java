package br.uem.gestaoresiduos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uem.gestaoresiduos.entities.ColetaResiduosSolidos;
import br.uem.gestaoresiduos.repositories.ColetaResiduosSolidosRepository;

@Service
@Transactional
public class ColetaResiduosSolidosService {
	
	@Autowired
	private ColetaResiduosSolidosRepository coletaResiduosSolidosRepository;
	
	public ColetaResiduosSolidos create(ColetaResiduosSolidos coletaResiduosSolidos) {
		return coletaResiduosSolidosRepository.save(coletaResiduosSolidos);
	}
	
	public ColetaResiduosSolidos findById(int id) {
		return coletaResiduosSolidosRepository.findOne(id);
	}
	
	public List<ColetaResiduosSolidos> findAll() {
		return coletaResiduosSolidosRepository.findAll();
	}
	
}
