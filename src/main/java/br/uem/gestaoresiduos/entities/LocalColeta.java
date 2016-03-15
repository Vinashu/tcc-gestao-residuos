package br.uem.gestaoresiduos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name="locais_coleta")
@DiscriminatorValue(value = "coleta")
public class LocalColeta extends Local implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="tipo_residuos")
	@Enumerated(value=EnumType.STRING)
	private TiposResiduos tipoResiduos;

	public TiposResiduos getTipoResiduos() {
		return tipoResiduos;
	}

	public void setTipoResiduos(TiposResiduos tipoResiduos) {
		this.tipoResiduos = tipoResiduos;
	}
	
}
