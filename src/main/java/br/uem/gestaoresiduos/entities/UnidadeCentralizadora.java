package br.uem.gestaoresiduos.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name="unidades_centralizadoras")
public class UnidadeCentralizadora implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="unid_id")
	private int id;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="campus_id")
	private Campus campus;
	private String bloco;
	private String sala;
	private String responsavel;
	private String telefone;
	
	@ManyToMany(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinTable(name="unid_centralizadora_to_local", joinColumns={@JoinColumn(name="unid_centralizadora_id", referencedColumnName="unid_id")}, inverseJoinColumns={@JoinColumn(name="loc_id", referencedColumnName="local_id")})
	private List<Local> locais;
	
	@Column(name="tipo_residuos")
	@Enumerated(value=EnumType.STRING)
	private TiposResiduos tipoResiduos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public TiposResiduos getTipoResiduos() {
		return tipoResiduos;
	}

	public void setTipoResiduos(TiposResiduos tipoResiduos) {
		this.tipoResiduos = tipoResiduos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + ((campus == null) ? 0 : campus.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result + ((sala == null) ? 0 : sala.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((tipoResiduos == null) ? 0 : tipoResiduos.hashCode());
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
		UnidadeCentralizadora other = (UnidadeCentralizadora) obj;
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
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
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
		if (tipoResiduos != other.tipoResiduos)
			return false;
		return true;
	}
	
	
	
	
}
