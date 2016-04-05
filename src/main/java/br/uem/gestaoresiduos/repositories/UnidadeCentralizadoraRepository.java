package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uem.gestaoresiduos.entities.TiposResiduos;
import br.uem.gestaoresiduos.entities.UnidadeCentralizadora;
import br.uem.gestaoresiduos.entities.User;

public interface UnidadeCentralizadoraRepository extends JpaRepository<UnidadeCentralizadora, Serializable>{
	
	List<UnidadeCentralizadora> findByTipoResiduosAndAtiva(TiposResiduos tiposResiduos, boolean ativa);
	
	List<UnidadeCentralizadora> findByTipoResiduosAndAtivaAndResponsavel(TiposResiduos tiposResiduos, boolean ativa, User responsavel);
}
