package br.uem.gestaoresiduos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uem.gestaoresiduos.entities.Campus;
import br.uem.gestaoresiduos.repositories.CampusRespository;
import br.uem.gestaoresiduos.repositories.LocalRepository;

@Service
@Transactional
public class CampusService {
	
	@Autowired
	private CampusRespository campusRespository;
	
	@Autowired
	private LocalRepository localRepository;
	
	
	@SuppressWarnings("unchecked")
	public List<Campus> findAll(){
		return (List<Campus>)(List<?>) localRepository.findAllCampus();
	}
	
	public Campus create(Campus campus) {
		return campusRespository.save(campus);
	}

	public Campus findById(int id) {
		return (Campus)localRepository.findOne(id);
	}
	
}
