package br.uem.gestaoresiduos.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.uem.gestaoresiduos.entities.Laboratorio;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Serializable>{

}
