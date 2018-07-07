package com.alvaro.udemy.springboot.jpa.demo.dao;

import com.alvaro.udemy.springboot.jpa.demo.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClienteDaoImpl implements ClienteDao {

    @PersistenceContext
    private EntityManager em;


    public Cliente findOne(Long id) {
        return em.find(Cliente.class, id);
    }

    @Override
    public List findAll() {
        return em.createQuery("from Cliente").getResultList();
    }


    @Override
    public void save(Cliente cliente) {

        if (cliente.getId() != null && cliente.getId() > 0)
            em.merge(cliente);
        else
            em.persist(cliente);
    }

    @Override
    public void delete(Long id) {
        em.remove(findOne(id));
    }
}
