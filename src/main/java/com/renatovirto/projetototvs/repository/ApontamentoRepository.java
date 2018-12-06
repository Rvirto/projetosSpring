package com.renatovirto.projetototvs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renatovirto.projetototvs.model.Apontamento;
import com.renatovirto.projetototvs.model.Funcionario;
import com.renatovirto.projetototvs.repository.apontamento.ApontamentoRepositoryQuery;

public interface ApontamentoRepository extends JpaRepository<Apontamento, Long>, ApontamentoRepositoryQuery {

	public List<Apontamento> findByFuncionarioOrderByIdDesc(Funcionario funcionario);
	
	public List<Apontamento> findAllByOrderByIdDesc();
}
