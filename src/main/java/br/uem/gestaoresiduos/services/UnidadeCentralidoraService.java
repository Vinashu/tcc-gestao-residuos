package br.uem.gestaoresiduos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uem.gestaoresiduos.entities.UnidadeCentralizadora;
import br.uem.gestaoresiduos.repositories.UnidadeCentralizadoraRepository;

@Service
@Transactional
public class UnidadeCentralidoraService {
	
	@Autowired
	private UnidadeCentralizadoraRepository unidadeCentralizadoraRepository;
	
	public List<UnidadeCentralizadora> findAll() {
		return unidadeCentralizadoraRepository.findAll();
	}
	
	public UnidadeCentralizadora create(UnidadeCentralizadora unidadeCentralizadora) {
		return unidadeCentralizadoraRepository.save(unidadeCentralizadora);
	}
}
