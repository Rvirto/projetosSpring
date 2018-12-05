package com.renatovirto.projetototvs.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.projetototvs.model.Funcionario;
import com.renatovirto.projetototvs.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioResource {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping
	public List<Funcionario> listarTodos() {
		return funcionarioService.listarTodos();
	}
	
	@GetMapping("/{id}")
	public Funcionario encontrarFuncionario(@PathVariable Long id) {
		return funcionarioService.encontrarFuncionario(id);
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> salvarFuncionario(@Valid @RequestBody Funcionario funcionario) {
		Funcionario funcionarioSalvo = funcionarioService.cadastrarFuncionario(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
	}
	
	@PutMapping
	public ResponseEntity<Funcionario> atualizarFuncionario(@Valid @RequestBody Funcionario funcionario) {
		Funcionario funcionarioAtualizado = funcionarioService.atualizaFuncionario(funcionario);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(funcionarioAtualizado);
	}
}
