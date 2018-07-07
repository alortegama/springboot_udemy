package com.alvaro.udemy.springboot.jpa.demo.dao;

import com.alvaro.udemy.springboot.jpa.demo.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDaoCrud extends CrudRepository<Cliente,Long> {


}
