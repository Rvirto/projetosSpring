package com.renatovirto.projetototvs.repository.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ApontamentoFilter {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataFim;
	
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	
}
