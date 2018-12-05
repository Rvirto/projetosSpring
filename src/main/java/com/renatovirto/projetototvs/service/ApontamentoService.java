package com.renatovirto.projetototvs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetototvs.model.Apontamento;
import com.renatovirto.projetototvs.model.Funcionario;
import com.renatovirto.projetototvs.repository.ApontamentoRepository;

@Service
public class ApontamentoService {

	@Autowired
	private ApontamentoRepository apontamentoRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	public List<Apontamento> todosApontamentos() {
		return apontamentoRepository.findAll();
	}
	
	public Apontamento encontrarApontamento(Long id) {
		Apontamento apontamentoBuscado = apontamentoRepository.findOne(id);
		
		if (apontamentoBuscado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return apontamentoBuscado;
	}
	
	public Apontamento salvarApontamento(Apontamento apontamento) {
		Apontamento apontamentoSalvo = new Apontamento();
		try {
			apontamentoSalvo = apontamentoRepository.save(apontamento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return apontamentoSalvo;
	}
	
	public Apontamento atualizarApontamento(Apontamento apontamento) {
		Apontamento apontamentoBuscado = new Apontamento();
		
		try {
			apontamento.setDataFim(new Date());
			apontamento.setQuantidadeHoras(calcularHoras(apontamento));
			apontamentoBuscado = encontrarApontamento(apontamento.getId());
			BeanUtils.copyProperties(apontamento, apontamentoBuscado, "id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return apontamentoRepository.save(apontamentoBuscado);
	}
	
	public void excluirApontamento(Long id) {
		apontamentoRepository.delete(id);
	}
	
	public Long calcularHoras(Apontamento apontamento) {
		Long quantidade = (long) apontamento.getDataFim().getHours() - (long) apontamento.getDataInicio().getHours();
		
		return quantidade;
	}
	
	public List<Apontamento> listarApontamentosFuncionario(Long id) {
		Funcionario funcionarioBuscado = funcionarioService.encontrarFuncionario(id);
		List<Apontamento> apontamentos = apontamentoRepository.findByFuncionario(funcionarioBuscado);
		
		return apontamentos;
	}
}
