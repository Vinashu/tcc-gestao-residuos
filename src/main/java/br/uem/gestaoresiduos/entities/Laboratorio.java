package br.uem.gestaoresiduos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect
@Entity
@Table(name="laboratorios")
@DiscriminatorValue(value = "lab")
public class Laboratorio  extends Local implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="campus_id")
	private Campus campus;
	
	private String bloco;
	private String sala;
	private String responsavel;
	private String telefone;
	private String email;
	
	@Column(name="tipo_atividade")
	private String tipoAtividade;
	
	@Column(name="tipo_residuos")
	@Enumerated(value=EnumType.STRING)
	private TiposResiduos tipoResiduos;
	
	@Column(name="residuo_quimico")
	private boolean residuoQuimico;
	
	@Column(name="residuo_hospitalar")
	private boolean residuoHospitalar;
	
	@Column(name="residuo_solido")
	private boolean residuoSolido;
	
	
	public boolean getResiduoQuimico() {
		return residuoQuimico;
	}

	public void setResiduoQuimico(boolean residuoQuimico) {
		this.residuoQuimico = residuoQuimico;
	}

	public boolean getResiduoHospitalar() {
		return residuoHospitalar;
	}

	public void setResiduoHospitalar(boolean residuoHospitalar) {
		this.residuoHospitalar = residuoHospitalar;
	}

	public boolean getResiduoSolido() {
		return residuoSolido;
	}

	public void setResiduoSolido(boolean residuoSolido) {
		this.residuoSolido = residuoSolido;
	}

	public String getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(String tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public TiposResiduos getTipoResiduos() {
		return tipoResiduos;
	}

	public void setTipoResiduos(TiposResiduos tipoResiduos) {
		this.tipoResiduos = tipoResiduos;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + ((campus == null) ? 0 : campus.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (residuoHospitalar ? 1231 : 1237);
		result = prime * result + (residuoQuimico ? 1231 : 1237);
		result = prime * result + (residuoSolido ? 1231 : 1237);
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result + ((sala == null) ? 0 : sala.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((tipoAtividade == null) ? 0 : tipoAtividade.hashCode());
		result = prime * result + ((tipoResiduos == null) ? 0 : tipoResiduos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Laboratorio other = (Laboratorio) obj;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (campus == null) {
			if (other.campus != null)
				return false;
		} else if (!campus.equals(other.campus))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (residuoHospitalar != other.residuoHospitalar)
			return false;
		if (residuoQuimico != other.residuoQuimico)
			return false;
		if (residuoSolido != other.residuoSolido)
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		if (sala == null) {
			if (other.sala != null)
				return false;
		} else if (!sala.equals(other.sala))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (tipoAtividade == null) {
			if (other.tipoAtividade != null)
				return false;
		} else if (!tipoAtividade.equals(other.tipoAtividade))
			return false;
		if (tipoResiduos != other.tipoResiduos)
			return false;
		return true;
	}
	
	
}
