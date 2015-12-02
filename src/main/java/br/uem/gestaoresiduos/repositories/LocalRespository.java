package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uem.gestaoresiduos.entities.Local;

public interface LocalRespository extends JpaRepository<Local, Serializable>{

}
