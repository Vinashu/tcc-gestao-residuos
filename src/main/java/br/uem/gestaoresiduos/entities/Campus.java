package br.uem.gestaoresiduos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * sem frontend... poucos e fixos campi.. cadastra na mao
 * NAO EXTENDER LOCAL.... Local depende de campus.
 */

@Entity
@Table(name="campus")
public class Campus implements Serializable{

	private static final long serialVersionUID = 5784864129037017707L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "campus_id")
	private int id;
	private String nome;
	
	@Enumerated(value=EnumType.STRING)
	private TipoCampus tipo;
	
	private String localizacao;
	
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
	public TipoCampus getTipo() {
		return tipo;
	}
	public void setTipo(TipoCampus tipo) {
		this.tipo = tipo;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((localizacao == null) ? 0 : localizacao.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Campus other = (Campus) obj;
		
		if (localizacao == null) {
			if (other.localizacao != null)
				return false;
		} else if (!localizacao.equals(other.localizacao))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
	
	
}
