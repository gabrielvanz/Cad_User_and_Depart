package br.edu.atitus.pooavancado.CadUsuario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.atitus.pooavancado.CadUsuario.Entities.Departamento;

@Repository
public interface DepartamentoRespository extends JpaRepository<Departamento, Long>{
	
	List<Departamento> findByNomeContainingIgnoreCase(String nome);
	
}
