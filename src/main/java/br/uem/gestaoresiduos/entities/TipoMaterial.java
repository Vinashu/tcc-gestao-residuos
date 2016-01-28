package br.uem.gestaoresiduos.entities;

public enum TipoMaterial {
	A("Infectante"), B("Qu�mico"), C("Radioativo"), D("Comum Recicl�vel"), E("P�rfuro Cortante");

	private final String descricao;
	
	private TipoMaterial(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
	
}
