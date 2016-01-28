package br.uem.gestaoresiduos.entities;

public enum TipoMaterial {
	A("Infectante"), B("Químico"), C("Radioativo"), D("Comum Reciclável"), E("Pérfuro Cortante");

	private final String descricao;
	
	private TipoMaterial(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
	
}
