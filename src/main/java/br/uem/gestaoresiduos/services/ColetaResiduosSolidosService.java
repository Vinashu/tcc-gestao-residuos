package br.uem.gestaoresiduos.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

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
	
	public Page<ColetaResiduosSolidos> findAll(JsonNode unitId, int page) {
		PageRequest pageableRequest = new PageRequest(page, 20);
		int unidCentralizadoraId = unitId.asInt();
		return coletaResiduosSolidosRepository.findByUnidadeCentralizadoraId(unidCentralizadoraId, pageableRequest);
	}

	public Page<ColetaResiduosSolidos> findByMesAnoLocalUnidCentralizadora(JsonNode search, int page) {
		PageRequest pageableRequest = new PageRequest(page, 20);
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); 
		
		int mes = search.get("mes").asInt();
		int ano = search.get("ano").asInt();
		int unidCentralizadoraId = search.get("unidCentralizadoraId").asInt();
		int localId = search.get("localId").asInt();
		Date start = null, end = null;
		
		try {
			start = sdf1.parse("01/"+mes+"/"+ano);
			end = sdf1.parse("31/"+mes+"/"+ano);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Page<ColetaResiduosSolidos> results = coletaResiduosSolidosRepository.findByDataColetaBetweenAndLocalIdAndUnidadeCentralizadoraId(start, end, localId, unidCentralizadoraId, pageableRequest);
		return results;
	}
	
}
