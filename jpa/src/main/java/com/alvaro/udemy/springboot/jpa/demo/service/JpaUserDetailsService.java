package com.alvaro.udemy.springboot.jpa.demo.service;

import com.alvaro.udemy.springboot.jpa.demo.dao.UsuarioDao;
import com.alvaro.udemy.springboot.jpa.demo.entity.Role;
import com.alvaro.udemy.springboot.jpa.demo.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {
	@Autowired
	private UsuarioDao usuarioDao;
	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioDao.findByUsername(username);
		if (user == null) {
			String error = "Error login: el usuario no existe".concat(username);
			log.error(error);
			throw new UsernameNotFoundException(error);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : user.getRoles()) {
			log.info("Role: ".concat(role.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		if (authorities.isEmpty()) {
			String error = "Error login: El usuario ".concat(username).concat(" NO tiene roles");
			log.error(error);
			throw new UsernameNotFoundException(error);
		}
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}
}
