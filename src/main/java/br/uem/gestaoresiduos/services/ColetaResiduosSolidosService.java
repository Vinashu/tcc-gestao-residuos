package br.uem.gestaoresiduos.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uem.gestaoresiduos.entities.ColetaResiduosSolidos;
import br.uem.gestaoresiduos.entities.Local;
import br.uem.gestaoresiduos.entities.UnidadeCentralizadora;
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

	public List<ColetaResiduosSolidos> findByMesAnoLocalUnidCentralizadora(int mes, int ano, Local local,
			UnidadeCentralizadora unidCentralizadora) {
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); 
		
		Date start = null, end = null;
		try {
			start = sdf1.parse("01/"+mes+"/"+ano);
			end = sdf1.parse("31/"+mes+"/"+ano);
		} catch (ParseException e) {
		}
		
		return coletaResiduosSolidosRepository.findByDataColetaBetweenAndLocalAndUnidadeCentralizadora(start, end, local, unidCentralizadora);
	}
	
}
