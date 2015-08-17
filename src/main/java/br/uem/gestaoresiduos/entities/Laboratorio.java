package br.uem.gestaoresiduos.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="laboratorios")
public class Laboratorio extends UnidadeGeradora{

	@Column(name="tipo_atividade")
	private String tipoAtividade;

	public String getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(String tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}
	
	
}
