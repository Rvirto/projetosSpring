package com.renatovirto.projetototvs.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetototvs.model.Apontamento;
import com.renatovirto.projetototvs.model.Atividades;
import com.renatovirto.projetototvs.repository.AtividadesRepository;

@Service
public class AtividadesService {

	@Autowired
	private AtividadesRepository atividadesRepository;
	
	public List<Atividades> encontrarTodos() {
		return atividadesRepository.findAll();
	}
	
	public Atividades encontrarAtividade(Long id) {
		Atividades atividadeBuscada = atividadesRepository.findOne(id);
		
		if ( atividadeBuscada == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return atividadeBuscada;
	}
	
	public Atividades atualizarAtividade(Atividades atividade) {
		Atividades atividadeBuscada = new Atividades();
		
		try {
			atividadeBuscada = encontrarAtividade(atividade.getId());
			BeanUtils.copyProperties(atividade, atividadeBuscada, "id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return atividadesRepository.save(atividadeBuscada);
	}
	
	public Atividades cadastrarAtividade(Atividades atividade) {
		Atividades atividadeSalva = new Atividades();
		try {
			atividadeSalva = atividadesRepository.save(atividade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return atividadeSalva;
	}
	
	public void excluirAtividade(Long id) {
		atividadesRepository.delete(id);
	}
	
	public void aumentaHoraAtividade(Apontamento apontamento) {
		Atividades atividade = apontamento.getAtividade();
		Long totalHoras = apontamento.getAtividade().getTotalHoras();
		Long soma = totalHoras + apontamento.getQuantidadeHoras();
		atividade.setTotalHoras(soma);
		Atividades atividadeBuscada = encontrarAtividade(apontamento.getAtividade().getId());
		BeanUtils.copyProperties(atividade, atividadeBuscada, "id");
		
		atividadesRepository.save(atividadeBuscada);
	}
}
