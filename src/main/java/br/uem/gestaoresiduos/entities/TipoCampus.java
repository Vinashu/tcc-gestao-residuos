package br.uem.gestaoresiduos.entities;

public enum TipoCampus {
	Sede("Campus Sede"),Regional("Campus Regional"), Fazenda("Fazenda Experimental"), CentroPesquisa("Centro de Pesquisa");
	
	private final String nomeCampus;
	
	private TipoCampus(String nome) {
		nomeCampus = nome;
	}
	
	public String getNomeCampus(){
		return nomeCampus;
	}
}
