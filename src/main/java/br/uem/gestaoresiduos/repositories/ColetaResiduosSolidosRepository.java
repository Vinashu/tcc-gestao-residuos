package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uem.gestaoresiduos.entities.ColetaResiduosSolidos;

public interface ColetaResiduosSolidosRepository extends JpaRepository<ColetaResiduosSolidos, Serializable>{
	
}
