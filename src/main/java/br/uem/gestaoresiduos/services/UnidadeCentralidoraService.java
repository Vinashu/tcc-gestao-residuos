package br.uem.gestaoresiduos.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uem.gestaoresiduos.entities.TiposResiduos;
import br.uem.gestaoresiduos.entities.UnidadeCentralizadora;
import br.uem.gestaoresiduos.repositories.UnidadeCentralizadoraRepository;

@Service
@Transactional
public class UnidadeCentralidoraService {

	static Logger log = Logger.getLogger(UnidadeCentralidoraService.class);

	@Autowired
	private UnidadeCentralizadoraRepository unidadeCentralizadoraRepository;

	public List<UnidadeCentralizadora> findAll() {
		return unidadeCentralizadoraRepository.findAll();
	}
	
	public List<UnidadeCentralizadora> findByTipoResiduos(TiposResiduos tiposResiduos) {
		return unidadeCentralizadoraRepository.findByTipoResiduosAndAtiva(tiposResiduos, true);
	}

	public UnidadeCentralizadora create(UnidadeCentralizadora unidadeCentralizadora) {
		UnidadeCentralizadora savedUnid = null;
		unidadeCentralizadora.setAtiva(true);
		try {
			savedUnid = unidadeCentralizadoraRepository.save(unidadeCentralizadora);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return savedUnid;
	}
	
	public UnidadeCentralizadora findById(int id) {
		return unidadeCentralizadoraRepository.findOne(id);
	}

	public void deleteById(int id) {
		UnidadeCentralizadora unid = unidadeCentralizadoraRepository.findOne(id);
		unid.setAtiva(false);
		unidadeCentralizadoraRepository.save(unid);
	}
}
