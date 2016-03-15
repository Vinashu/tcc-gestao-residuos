package br.uem.gestaoresiduos.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uem.gestaoresiduos.entities.TiposResiduos;

@Service
@Transactional
public class TiposResiduosService {
	
	public String findAll() {
			List<TiposResiduos> residuos = Arrays.asList(TiposResiduos.values());
			
			StringBuffer retorno = new StringBuffer();
			retorno.append("[");
			
			for (TiposResiduos tipo : residuos) {
				retorno.append("{\"tipoResiduos\":\"" + tipo + "\",\"tipo\":\""+tipo.getTipo()+"\"},");
			}
			retorno.deleteCharAt(retorno.length() - 1);
			retorno.append("]");
			
			return retorno.toString();
	}

}
