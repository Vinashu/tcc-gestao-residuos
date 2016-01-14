package br.uem.gestaoresiduos.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		Date start = null, end = null;
		int mes, ano, unidCentralizadoraId, localId;
		Page<ColetaResiduosSolidos> results = null;
		
		if (search.has("mes") && search.get("mes").asInt() > 0 && search.has("ano")) {
			mes = search.get("mes").asInt();
			ano = search.get("ano").asInt();
			unidCentralizadoraId = search.get("unidCentralizadoraId").asInt();
			localId = search.get("localId").asInt();
			
			try {
				start = sdf1.parse("01/"+mes+"/"+ano);
				end = sdf1.parse("31/"+mes+"/"+ano);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			results = localId > 0 ? coletaResiduosSolidosRepository.findByDataColetaBetweenAndLocalIdAndUnidadeCentralizadoraId(start, end, localId, unidCentralizadoraId, pageableRequest) 
					: coletaResiduosSolidosRepository.findByDataColetaBetweenAndUnidadeCentralizadoraId(start, end, unidCentralizadoraId, pageableRequest) ;
					
		}else if (search.has("ano")) {
			
			ano = search.get("ano").asInt();
			unidCentralizadoraId = search.get("unidCentralizadoraId").asInt();
			localId = search.get("localId").asInt();
			
			try {
				start = sdf1.parse("01/01/"+ano);
				end = sdf1.parse("31/12/"+ano);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			results = localId > 0 ? coletaResiduosSolidosRepository.findByDataColetaBetweenAndLocalIdAndUnidadeCentralizadoraId(start, end, localId, unidCentralizadoraId, pageableRequest) 
					: coletaResiduosSolidosRepository.findByDataColetaBetweenAndUnidadeCentralizadoraId(start, end, unidCentralizadoraId, pageableRequest) ;
		}else{
			unidCentralizadoraId = search.get("unidCentralizadoraId").asInt();
			localId = search.get("localId").asInt();
			
			results = localId > 0 ? coletaResiduosSolidosRepository.findByLocalIdAndUnidadeCentralizadoraId(localId, unidCentralizadoraId, pageableRequest) 
					: coletaResiduosSolidosRepository.findByUnidadeCentralizadoraId(unidCentralizadoraId, pageableRequest) ;
		}
		
		return results;
	}

	public boolean deleteById(int id) {
		ColetaResiduosSolidos coleta = coletaResiduosSolidosRepository.findOne(id);
		if (coleta != null) {
			coletaResiduosSolidosRepository.delete(coleta);
			return true;
		}
		return false;
	}
	
}
