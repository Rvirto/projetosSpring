package com.renatovirto.projetototvs.repository.apontamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.renatovirto.projetototvs.model.Apontamento;
import com.renatovirto.projetototvs.repository.filter.ApontamentoFilter;

public class ApontamentoRepositoryImpl implements ApontamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Apontamento> filtrar(ApontamentoFilter apontamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Apontamento> criteria = builder.createQuery(Apontamento.class);
		
		Root<Apontamento> root = criteria.from(Apontamento.class);
		
		Predicate[] predicates = criarRestricoes(apontamentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Apontamento> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(ApontamentoFilter apontamentoFilter, CriteriaBuilder builder,
			Root<Apontamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (apontamentoFilter.getDataInicio() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataInicio"), apontamentoFilter.getDataInicio()));
		}
		
		if (apontamentoFilter.getDataFim() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataFim"), apontamentoFilter.getDataFim()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
