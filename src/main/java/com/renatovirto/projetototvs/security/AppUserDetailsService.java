package com.renatovirto.projetototvs.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.renatovirto.projetototvs.model.Funcionario;
import com.renatovirto.projetototvs.repository.FuncionarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		Funcionario funcionario = funcionarioRepository.findByNome(nome);
		
		if ( funcionario == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return new UsuarioSistema(funcionario, getPermissao(funcionario));
	}

	private Collection<? extends GrantedAuthority> getPermissao(Funcionario funcionario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(funcionario.getTipoPermissao().toString()));
		return authorities;
	}
	
}
