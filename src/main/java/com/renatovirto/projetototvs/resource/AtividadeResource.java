package com.renatovirto.projetototvs.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renatovirto.projetototvs.model.Atividades;
import com.renatovirto.projetototvs.service.AtividadesService;

@RestController
@RequestMapping("/atividades")
public class AtividadeResource {

	@Autowired
	private AtividadesService atividadesService;
	
	@GetMapping
	public List<Atividades> listarTodos() {
		return atividadesService.encontrarTodos();
	}
	
	@GetMapping("/{id}")
	public Atividades encontrarAtividade(@PathVariable Long id) {
		return atividadesService.encontrarAtividade(id);
	}
	
	@PostMapping
	public ResponseEntity<Atividades> cadastrarAtividade(@Valid @RequestBody Atividades atividade) {
		Atividades atividadeSalvo = atividadesService.cadastrarAtividade(atividade);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(atividadeSalvo);
	}
	
	@PutMapping
	public ResponseEntity<Atividades> atualizarAtividade(@Valid @RequestBody Atividades atividade) {
		Atividades atividadeAtualizada = atividadesService.atualizarAtividade(atividade);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(atividadeAtualizada);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Atividades> excluirAtividade(@PathVariable Long id) {
		atividadesService.excluirAtividade(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
