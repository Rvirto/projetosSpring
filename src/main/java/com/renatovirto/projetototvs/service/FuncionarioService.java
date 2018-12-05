package com.renatovirto.projetototvs.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetototvs.model.Funcionario;
import com.renatovirto.projetototvs.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public List<Funcionario> listarTodos() {
		return funcionarioRepository.findAll();
	}
	
	public Funcionario encontrarFuncionario(Long id) {
		Funcionario funcionariobuscado = funcionarioRepository.findOne(id);
		
		if (funcionariobuscado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return funcionariobuscado;
	}
	
	public Funcionario cadastrarFuncionario(Funcionario funcionario) {
		Funcionario funcionarioSalvo = new Funcionario();
		try {
			funcionarioSalvo = funcionarioRepository.save(funcionario);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funcionarioSalvo;
	}
	
	public Funcionario atualizaFuncionario(Funcionario funcionario) {
		Funcionario funcionarioBuscado = new Funcionario();
		try {
			funcionarioBuscado = encontrarFuncionario(funcionario.getId());
			BeanUtils.copyProperties(funcionario, funcionarioBuscado, "id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funcionarioRepository.save(funcionarioBuscado);
	}
}
