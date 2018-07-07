package com.alvaro.udemy.springboot.jpa.demo.service;


import com.alvaro.udemy.springboot.jpa.demo.dao.ClienteDaoCrud;
import com.alvaro.udemy.springboot.jpa.demo.dao.ClienteDaoCrudPagination;
import com.alvaro.udemy.springboot.jpa.demo.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    //private ClienteDaoCrud clienteDao;
    //private ClienteDao clienteDao;
    private ClienteDaoCrudPagination clienteDao;


    @Override
    @Transactional(readOnly = true)
    public List findAll() {
        return (List) clienteDao.findAll();
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteDao.findAll(pageable);
    }


    @Override
    @Transactional
    public void save(Cliente cliente) {
        clienteDao.save(cliente);

    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteDao.deleteById(id);

    }
}
