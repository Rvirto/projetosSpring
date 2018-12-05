package com.renatovirto.projetototvs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renatovirto.projetototvs.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	public Funcionario findByNome(String nome);
}
