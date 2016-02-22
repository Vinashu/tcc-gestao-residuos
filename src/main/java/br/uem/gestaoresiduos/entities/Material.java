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

import com.fasterxml.jackson.annotation.JsonAutoDetect;


//ALTERAR TIPO MATERIAL PARA QUIM/HOSP/SOLIDO... CRIAR TIPOMATERIALSERVICOSAUDE COM A, B, C, D, E

@JsonAutoDetect
@Entity
@Table(name = "material")
public class Material implements Serializable {

	private static final long serialVersionUID = -1926295067621453665L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "material_id")
	private int id;

	@Column(unique=true)
	private String descricao;

	@Enumerated(EnumType.STRING)
	private TipoMaterial tipo;

	public Material() {
		super();
	}

	public Material(String descricao, TipoMaterial tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
