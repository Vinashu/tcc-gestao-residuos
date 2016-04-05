package br.uem.gestaoresiduos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name="reuso")
public class Reuso implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reuso_id")
	private int id;

	@OneToOne
	@JoinColumn(name="material_coletado_id")
	private MaterialColetado materialColetado;
	
	@ManyToOne
	@JoinColumn(name="responsavel_id")
	private User responsavel;
	
	private String consumidor;
	
	private Date entrada;
	
	private Date saida;
	
	@ManyToOne
	@JoinColumn(name="campus_id")
	private Campus campus;
	
	@Enumerated(EnumType.STRING)
	private StatusReuso status;
	
	public MaterialColetado getMaterialColetado() {
		return materialColetado;
	}

	public void setMaterialColetado(MaterialColetado materialColetado) {
		this.materialColetado = materialColetado;
	}

	public User getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(User responsavel) {
		this.responsavel = responsavel;
	}

	public String getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(String consumidor) {
		this.consumidor = consumidor;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StatusReuso getStatus() {
		return status;
	}

	public void setStatus(StatusReuso status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consumidor == null) ? 0 : consumidor.hashCode());
		result = prime * result + ((entrada == null) ? 0 : entrada.hashCode());
		result = prime * result + ((materialColetado == null) ? 0 : materialColetado.hashCode());
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result + ((saida == null) ? 0 : saida.hashCode());
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
		Reuso other = (Reuso) obj;
		if (consumidor == null) {
			if (other.consumidor != null)
				return false;
		} else if (!consumidor.equals(other.consumidor))
			return false;
		if (entrada == null) {
			if (other.entrada != null)
				return false;
		} else if (!entrada.equals(other.entrada))
			return false;
		if (materialColetado == null) {
			if (other.materialColetado != null)
				return false;
		} else if (!materialColetado.equals(other.materialColetado))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		if (saida == null) {
			if (other.saida != null)
				return false;
		} else if (!saida.equals(other.saida))
			return false;
		return true;
	}
	
	
	
}
