package br.uem.gestaoresiduos.entities;

public enum UnidadeMedida {
	UNIDADE("Unidades", "Unid"), QUILO("Quilos", "Kg"), LITRO("Litros", "L");
	
	private final String nome;
	private final String sigla;
	
	private UnidadeMedida(String nome, String sigla) {
		this.nome = nome;
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public String getSigla() {
		return sigla;
	}
	
	
}
