package br.uem.gestaoresiduos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/*
 * add atributo boolean disponivelParaReuso e criar tabela materialcoletado_to_localarmazenado
 */

@JsonAutoDetect
@Entity
@Table(name="material_coletado")
public class MaterialColetado implements Serializable{
	
	private static final long serialVersionUID = -3043704756277318240L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="material_coletado_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="material_id")
	private Material material;
	
	private Double quantidade;
	
	@Enumerated(EnumType.STRING)
	@Column(name="unidade_medida")
	private UnidadeMedida unidadeMedida;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
}
