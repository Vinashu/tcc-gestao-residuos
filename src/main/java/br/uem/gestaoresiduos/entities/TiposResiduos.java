package br.uem.gestaoresiduos.entities;

public enum TiposResiduos {
	SOLIDO("S�lido"), QUIMICO("Qu�mico"), SERVICOSAUDE("Servi�o de Sa�de");
	
	private final String tipo;

	private TiposResiduos(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
	
}
