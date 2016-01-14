package br.uem.gestaoresiduos.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import br.uem.gestaoresiduos.entities.ColetaResiduosQuimicos;
import br.uem.gestaoresiduos.repositories.ColetaResiduosQuimicosRepository;

@Service
public class ColetaResiduosQuimicosService {
	
	@Autowired
	private ColetaResiduosQuimicosRepository coletaResiduosQuimicosRepository;

	public ColetaResiduosQuimicos create(ColetaResiduosQuimicos coletaResiduosQuimicos) {
		return coletaResiduosQuimicosRepository.save(coletaResiduosQuimicos);
	}

	public Page<ColetaResiduosQuimicos> findByMesAnoLocalUnidCentralizadora(JsonNode search, Integer page) {
		PageRequest pageableRequest = new PageRequest(page, 20);
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); 
		Date start = null, end = null;
		int mes, ano, unidCentralizadoraId, labId;
		Page<ColetaResiduosQuimicos> results = null;
		
		if (search.has("mes") && search.get("mes").asInt() > 0 && search.has("ano")) {
			mes = search.get("mes").asInt();
			ano = search.get("ano").asInt();
			unidCentralizadoraId = search.get("unidCentralizadoraId").asInt();
			labId = search.get("labId").asInt();
			
			try {
				start = sdf1.parse("01/"+mes+"/"+ano);
				end = sdf1.parse("31/"+mes+"/"+ano);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			results = labId > 0 ? coletaResiduosQuimicosRepository.findByDataColetaBetweenAndLaboratorioIdAndUnidadeCentralizadoraId(start, end, labId, unidCentralizadoraId, pageableRequest) 
					: coletaResiduosQuimicosRepository.findByDataColetaBetweenAndUnidadeCentralizadoraId(start, end, unidCentralizadoraId, pageableRequest) ;
					
		}else if (search.has("ano")) {
			
			ano = search.get("ano").asInt();
			unidCentralizadoraId = search.get("unidCentralizadoraId").asInt();
			labId = search.get("labId").asInt();
			
			try {
				start = sdf1.parse("01/01/"+ano);
				end = sdf1.parse("31/12/"+ano);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			results = labId > 0 ? coletaResiduosQuimicosRepository.findByDataColetaBetweenAndLaboratorioIdAndUnidadeCentralizadoraId(start, end, labId, unidCentralizadoraId, pageableRequest) 
					: coletaResiduosQuimicosRepository.findByDataColetaBetweenAndUnidadeCentralizadoraId(start, end, unidCentralizadoraId, pageableRequest) ;
		}else{
			unidCentralizadoraId = search.get("unidCentralizadoraId").asInt();
			labId = search.get("labId").asInt();
			
			results = labId > 0 ? coletaResiduosQuimicosRepository.findByLaboratorioIdAndUnidadeCentralizadoraId(labId, unidCentralizadoraId, pageableRequest) 
					: coletaResiduosQuimicosRepository.findByUnidadeCentralizadoraId(unidCentralizadoraId, pageableRequest) ;
		}
		
		return results;
	}

	public Page<ColetaResiduosQuimicos> findAll(JsonNode unidId, Integer page) {
		PageRequest pageableRequest = new PageRequest(page, 20);
		int unidCentralizadoraId = unidId.asInt();
		return coletaResiduosQuimicosRepository.findByUnidadeCentralizadoraId(unidCentralizadoraId, pageableRequest);
	}

	public ColetaResiduosQuimicos findById(int id) {
		return coletaResiduosQuimicosRepository.findById(id);
	}

	public boolean deleteColeta(int id) {
		ColetaResiduosQuimicos coleta = coletaResiduosQuimicosRepository.findOne(id);
		if (coleta != null) {
			coletaResiduosQuimicosRepository.delete(coleta);
			return true;
		}
		return false;
	}
	

}
