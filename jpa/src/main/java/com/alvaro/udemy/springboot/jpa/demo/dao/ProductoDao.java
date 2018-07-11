package com.alvaro.udemy.springboot.jpa.demo.dao;

import com.alvaro.udemy.springboot.jpa.demo.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoDao extends CrudRepository<Producto,Long> {

	@Query("SELECT p FROM Producto p WHERE p.nombre like %?1%")
	public List<Producto> findByNombre(String term);


	public List<Producto> findByNombreLikeIgnoreCase(String term);
}
