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

import br.uem.gestaoresiduos.entities.ColetaResiduosHospitalares;
import br.uem.gestaoresiduos.repositories.ColetaResiduosHospitalaresRepository;

@Service
@Transactional
public class ColetaResiduosHospitalaresService {

	@Autowired
	private ColetaResiduosHospitalaresRepository coletaResiduosHospitalaresRepository;
	
	public ColetaResiduosHospitalares create(ColetaResiduosHospitalares coletaResiduosHospitalares) {
		return coletaResiduosHospitalaresRepository.save(coletaResiduosHospitalares);
	}
	
	public ColetaResiduosHospitalares findById(int id) {
		return coletaResiduosHospitalaresRepository.findOne(id);
	}
	
	public Page<ColetaResiduosHospitalares> findAll(JsonNode unitId, int page) {
		PageRequest pageableRequest = new PageRequest(page, 20);
		int unidCentralizadoraId = unitId.asInt();
		return coletaResiduosHospitalaresRepository.findByUnidadeCentralizadoraId(unidCentralizadoraId, pageableRequest);
	}

	public Page<ColetaResiduosHospitalares> findByMesAnoLocalUnidCentralizadora(JsonNode search, int page) {
		PageRequest pageableRequest = new PageRequest(page, 20);
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); 
		Date start = null, end = null;
		int mes, ano, unidCentralizadoraId, localId;
		Page<ColetaResiduosHospitalares> results = null;
		
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
			
			results = localId > 0 ? coletaResiduosHospitalaresRepository.findByDataColetaBetweenAndLocalIdAndUnidadeCentralizadoraId(start, end, localId, unidCentralizadoraId, pageableRequest) 
					: coletaResiduosHospitalaresRepository.findByDataColetaBetweenAndUnidadeCentralizadoraId(start, end, unidCentralizadoraId, pageableRequest) ;
					
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
			
			results = localId > 0 ? coletaResiduosHospitalaresRepository.findByDataColetaBetweenAndLocalIdAndUnidadeCentralizadoraId(start, end, localId, unidCentralizadoraId, pageableRequest) 
					: coletaResiduosHospitalaresRepository.findByDataColetaBetweenAndUnidadeCentralizadoraId(start, end, unidCentralizadoraId, pageableRequest) ;
		}else{
			unidCentralizadoraId = search.get("unidCentralizadoraId").asInt();
			localId = search.get("localId").asInt();
			
			results = localId > 0 ? coletaResiduosHospitalaresRepository.findByLocalIdAndUnidadeCentralizadoraId(localId, unidCentralizadoraId, pageableRequest) 
					: coletaResiduosHospitalaresRepository.findByUnidadeCentralizadoraId(unidCentralizadoraId, pageableRequest) ;
		}
		
		return results;
	}

	public boolean deleteById(int id) {
		ColetaResiduosHospitalares coleta = coletaResiduosHospitalaresRepository.findOne(id);
		if (coleta != null) {
			coletaResiduosHospitalaresRepository.delete(coleta);
			return true;
		}
		return false;
	}
}
