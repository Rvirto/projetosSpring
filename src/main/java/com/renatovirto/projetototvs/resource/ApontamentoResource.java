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

import com.renatovirto.projetototvs.model.Apontamento;
import com.renatovirto.projetototvs.repository.filter.ApontamentoFilter;
import com.renatovirto.projetototvs.service.ApontamentoService;
import com.renatovirto.projetototvs.service.AtividadesService;

@RestController
@RequestMapping("/apontamentos")
public class ApontamentoResource {

	@Autowired
	private ApontamentoService apontamentoService;
	
	@Autowired
	private AtividadesService atividadeService;
	
	@GetMapping
	public List<Apontamento> listarTodos() {
		return apontamentoService.todosApontamentos();
	}
	
	@GetMapping("/busca")
	public List<Apontamento> buscarComParametros(ApontamentoFilter apontamentoFilter) {
		return apontamentoService.encontrarApontamento(apontamentoFilter);
	}
	
	@GetMapping("/{id}")
	public Apontamento encontrarApontamento(@PathVariable Long id) {
		return apontamentoService.encontrarApontamento(id);
	}
	
	@PostMapping
	public ResponseEntity<Apontamento> cadastrarApontamento(@RequestBody Apontamento apontamento) {
		Apontamento apontamentoSalvo = apontamentoService.salvarApontamento(apontamento);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(apontamentoSalvo);
	}
	
	@PutMapping
	public ResponseEntity<Apontamento> atualizarApontamento(@Valid @RequestBody Apontamento apontamento) {
		Apontamento apontamentoAtualizado = apontamentoService.atualizarApontamento(apontamento);
		atividadeService.aumentaHoraAtividade(apontamentoAtualizado);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(apontamentoAtualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Apontamento> deletarApontamento(@PathVariable Long id) {
		apontamentoService.excluirApontamento(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/meusApontamentos/{idFuncionario}")
	public List<Apontamento> apontamentosFuncionario(@PathVariable Long idFuncionario) {
		return apontamentoService.listarApontamentosFuncionario(idFuncionario);
	}
}
