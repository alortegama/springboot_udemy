package com.alvaro.udemy.springboot.jpa.demo.controllers;

import com.alvaro.udemy.springboot.jpa.demo.service.ClienteService;
import com.alvaro.udemy.springboot.jpa.demo.view.xml.ClientList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

	@Autowired
	ClienteService clienteService;

	@GetMapping(value = "/listar")
	public ClientList index() {
		return new ClientList(clienteService.findAll());
	}
}
