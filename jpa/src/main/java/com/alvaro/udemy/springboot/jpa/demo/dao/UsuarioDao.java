package com.alvaro.udemy.springboot.jpa.demo.dao;

import com.alvaro.udemy.springboot.jpa.demo.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {

	public Usuario findByUsername(String username);
}
