package br.uem.gestaoresiduos.entities;

public enum TiposResiduos {
	SOLIDO("Sólido"), QUIMICO("Químico"), HOSPITALAR("Hospitalar");
	
	private final String tipo;

	private TiposResiduos(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
	
}
