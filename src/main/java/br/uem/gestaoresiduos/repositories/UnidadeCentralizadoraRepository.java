package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uem.gestaoresiduos.entities.UnidadeCentralizadora;

public interface UnidadeCentralizadoraRepository extends JpaRepository<UnidadeCentralizadora, Serializable>{

}
