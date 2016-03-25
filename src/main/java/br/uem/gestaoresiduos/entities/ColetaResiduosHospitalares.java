package br.uem.gestaoresiduos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name = "coleta_residuos_hospitalares")
public class ColetaResiduosHospitalares implements Serializable {

	private static final long serialVersionUID = 128284023619815756L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="coleta_residuos_hospitalares_id")
	private int id;

	@Column(name = "data_coleta")
	private Date dataColeta;

	@ManyToOne
	@JoinColumn(name = "unid_id")
	private UnidadeCentralizadora unidadeCentralizadora;

	@ManyToOne
	@JoinColumn(name = "local_id")
	private Local local;
	
	private Double qtdA;
	private Double qtdB;
	private Double qtdC;
	private Double qtdDcomum;
	private Double qtdDreciclavel;
	private Double qtdE;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public UnidadeCentralizadora getUnidadeCentralizadora() {
		return unidadeCentralizadora;
	}

	public void setUnidadeCentralizadora(UnidadeCentralizadora unidadeCentralizadora) {
		this.unidadeCentralizadora = unidadeCentralizadora;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Double getQtdA() {
		return qtdA;
	}

	public void setQtdA(Double qtdA) {
		this.qtdA = qtdA;
	}

	public Double getQtdB() {
		return qtdB;
	}

	public void setQtdB(Double qtdB) {
		this.qtdB = qtdB;
	}

	public Double getQtdC() {
		return qtdC;
	}

	public void setQtdC(Double qtdC) {
		this.qtdC = qtdC;
	}

	public Double getQtdDcomum() {
		return qtdDcomum;
	}

	public void setQtdDcomum(Double qtdD) {
		this.qtdDcomum = qtdD;
	}
	
	public Double getQtdDreciclavel() {
		return qtdDreciclavel;
	}

	public void setQtdDreciclavel(Double qtdD) {
		this.qtdDreciclavel = qtdD;
	}

	public Double getQtdD() {
		return qtdDcomum;
	}

	public void setQtdD(Double qtdD) {
		this.qtdDcomum = qtdD;
	}

	
	public Double getQtdE() {
		return qtdE;
	}

	public void setQtdE(Double qtdE) {
		this.qtdE = qtdE;
	}

}