package com.renatovirto.projetototvs.service;

import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetototvs.model.Apontamento;
import com.renatovirto.projetototvs.model.Funcionario;
import com.renatovirto.projetototvs.repository.ApontamentoRepository;
import com.renatovirto.projetototvs.repository.filter.ApontamentoFilter;

@Service
public class ApontamentoService {

	@Autowired
	private ApontamentoRepository apontamentoRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	public List<Apontamento> todosApontamentos() {
		return apontamentoRepository.findAllByOrderByIdDesc();
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
		LocalDateTime dataFinal = convertToLocalDateTimeViaInstant(apontamento.getDataFim());
		LocalDateTime dataInicio = convertToLocalDateTimeViaInstant(apontamento.getDataInicio());
		Long quantidade = ChronoUnit.HOURS.between(dataInicio, dataFinal);
		return quantidade;
	}
	
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	public List<Apontamento> listarApontamentosFuncionario(Long id) {
		Funcionario funcionarioBuscado = funcionarioService.encontrarFuncionario(id);
		List<Apontamento> apontamentos = apontamentoRepository.findByFuncionarioOrderByIdDesc(funcionarioBuscado);
		
		return apontamentos;
	}
	
	public List<Apontamento> encontrarApontamento(ApontamentoFilter apontamentoFilter) {
		return apontamentoRepository.filtrar(apontamentoFilter);
	}
}
