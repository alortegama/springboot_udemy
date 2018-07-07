package com.alvaro.udemy.springboot.jpa.demo.dao;

import com.alvaro.udemy.springboot.jpa.demo.entity.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClienteDaoCrudPagination extends PagingAndSortingRepository<Cliente,Long> {


}
