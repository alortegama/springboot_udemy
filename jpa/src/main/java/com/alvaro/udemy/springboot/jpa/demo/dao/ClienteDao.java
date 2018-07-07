package com.alvaro.udemy.springboot.jpa.demo.dao;

import com.alvaro.udemy.springboot.jpa.demo.entity.Cliente;

import java.util.List;

public interface ClienteDao {

    public List findAll();

    public void save(Cliente cliente);

    public Cliente findOne(Long id);

    public void delete(Long id);


}
