package br.uem.gestaoresiduos.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity(name="coleta_residuos_solidos")
public class ColetaResiduosSolidos {
	
	private int id;
	private int OS;
	private float peso;
	private Local local;
	private String observacoes;
	private Date dataColeta;
	
	@ManyToOne
	@JoinColumn(name="unid_centralizadora_id")
	private UnidadeCentralizadora unidadeCentralizadora;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOS() {
		return OS;
	}
	public void setOS(int oS) {
		OS = oS;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public UnidadeCentralizadora getUnidadeCentralizadora() {
		return unidadeCentralizadora;
	}
	public void setUnidadeCentralizadora(UnidadeCentralizadora unidadeCentralizadora) {
		this.unidadeCentralizadora = unidadeCentralizadora;
	}
	public Date getDataColeta() {
		return dataColeta;
	}
	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + OS;
		result = prime * result + ((dataColeta == null) ? 0 : dataColeta.hashCode());
		result = prime * result + id;
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + Float.floatToIntBits(peso);
		result = prime * result + ((unidadeCentralizadora == null) ? 0 : unidadeCentralizadora.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColetaResiduosSolidos other = (ColetaResiduosSolidos) obj;
		if (OS != other.OS)
			return false;
		if (dataColeta == null) {
			if (other.dataColeta != null)
				return false;
		} else if (!dataColeta.equals(other.dataColeta))
			return false;
		if (id != other.id)
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (Float.floatToIntBits(peso) != Float.floatToIntBits(other.peso))
			return false;
		if (unidadeCentralizadora == null) {
			if (other.unidadeCentralizadora != null)
				return false;
		} else if (!unidadeCentralizadora.equals(other.unidadeCentralizadora))
			return false;
		return true;
	}
	
	
	
}
