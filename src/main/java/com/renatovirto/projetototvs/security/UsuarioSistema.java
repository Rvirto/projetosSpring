package com.renatovirto.projetototvs.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.renatovirto.projetototvs.model.Funcionario;

public class UsuarioSistema extends User{
	
	private Funcionario funcionario;

	public UsuarioSistema(Funcionario funcionario, Collection<? extends GrantedAuthority> authorities) {
		super(funcionario.getNome(), funcionario.getSenha(), authorities);
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}
	private static final long serialVersionUID = 1L;

}
