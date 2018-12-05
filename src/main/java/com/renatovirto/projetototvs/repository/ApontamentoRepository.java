package com.renatovirto.projetototvs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renatovirto.projetototvs.model.Apontamento;
import com.renatovirto.projetototvs.model.Funcionario;

public interface ApontamentoRepository extends JpaRepository<Apontamento, Long> {

	public List<Apontamento> findByFuncionario(Funcionario funcionario);
}
