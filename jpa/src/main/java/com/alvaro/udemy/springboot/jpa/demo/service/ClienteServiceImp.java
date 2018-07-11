package com.alvaro.udemy.springboot.jpa.demo.service;


import com.alvaro.udemy.springboot.jpa.demo.dao.ClienteDaoCrudPagination;
import com.alvaro.udemy.springboot.jpa.demo.dao.FacturaDao;
import com.alvaro.udemy.springboot.jpa.demo.dao.ProductoDao;
import com.alvaro.udemy.springboot.jpa.demo.entity.Cliente;
import com.alvaro.udemy.springboot.jpa.demo.entity.Factura;
import com.alvaro.udemy.springboot.jpa.demo.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    //private ClienteDaoCrud clienteDao;
    //private ClienteDao clienteDao;
    private ClienteDaoCrudPagination clienteDao;

    @Autowired
    private ProductoDao productoDao;

    @Autowired
	private FacturaDao facturaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);

	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombreLikeIgnoreCase("%"+term+"%");
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
	}

	@Override
	@Transactional(readOnly=true)
	public Producto findProductoById(Long id) {
		return productoDao.findById(id).get();
	}

	@Override
	@Transactional(readOnly=true)
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).get();
	}

	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaDao.deleteById(id); // facturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Factura fetchFacturaByIdWithClienteWhithItemFacturaWithProducto(Long id) {
		return facturaDao.fetchByIdWithClienteWhithItemFacturaWithProducto(id);
	}
}
