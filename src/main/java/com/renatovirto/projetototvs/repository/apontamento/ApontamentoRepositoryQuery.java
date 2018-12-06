package com.renatovirto.projetototvs.repository.apontamento;

import java.util.List;

import com.renatovirto.projetototvs.model.Apontamento;
import com.renatovirto.projetototvs.repository.filter.ApontamentoFilter;

public interface ApontamentoRepositoryQuery{

	public List<Apontamento> filtrar(ApontamentoFilter apontamentoFilter);
}
