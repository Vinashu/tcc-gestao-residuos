package br.uem.gestaoresiduos.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="coleta_hospitalar_id", referencedColumnName="coleta_residuos_hospitalares_id")
	private List<MaterialColetado> materiaisColetados;

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

	public List<MaterialColetado> getMateriaisColetados() {
		return materiaisColetados;
	}

	public void setMateriaisColetados(List<MaterialColetado> materiaisColetados) {
		this.materiaisColetados = materiaisColetados;
	}

}