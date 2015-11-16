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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect
@Entity
@Table(name="laboratorios")
public class Laboratorio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nome;
	private String sigla;
	
	@ManyToOne
	@JoinColumn(name="campus_id")
	private Campus campus;
	
	private String bloco;
	private String sala;
	private String responsavel;
	private String telefone;
	private String email;
	
	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
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
	
}
