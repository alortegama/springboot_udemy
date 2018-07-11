package com.alvaro.udemy.springboot.jpa.demo.service;

import com.alvaro.udemy.springboot.jpa.demo.entity.Cliente;
import com.alvaro.udemy.springboot.jpa.demo.entity.Factura;
import com.alvaro.udemy.springboot.jpa.demo.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ClienteService {

    public List<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);

    public void save(Cliente cliente);

    public Cliente findOne(Long id);

    public void delete(Long id);

    public List<Producto> findByNombre(String term);

    public void saveFactura(Factura factura);

    public Producto findProductoById(Long id);

    public Factura findFacturaById(Long id);

    public void deleteFactura(Long id);

    public Factura fetchFacturaByIdWithClienteWhithItemFacturaWithProducto(Long id);
}
