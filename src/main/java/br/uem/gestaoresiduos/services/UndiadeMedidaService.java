package br.uem.gestaoresiduos.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.uem.gestaoresiduos.entities.UnidadeMedida;

@Service
public class UndiadeMedidaService {

	
	public ArrayNode listUnidadesMedida() {
		List<UnidadeMedida> unidades = Arrays.asList(UnidadeMedida.values());
		
		ObjectNode jsonUnid = JsonNodeFactory.instance.objectNode();
		ArrayNode jsonUnidades = JsonNodeFactory.instance.arrayNode();
		
		
		for (UnidadeMedida unid : unidades) {
			jsonUnidades.add("{\"unidade\":\"" + unid + "\",\"nome\":\""+unid.getNome()+"\",\"sigla\":\""+unid.getSigla()+"\"}");
		}
		
		return jsonUnidades;
	}
	
}
