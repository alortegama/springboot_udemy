package com.alvaro.udemy.springboot.jpa.demo.view.xml;

import com.alvaro.udemy.springboot.jpa.demo.entity.Cliente;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "clientes")
public class ClientList {

	@XmlElement(name = "cliente")
	private List<Cliente> clientes;

	public ClientList() {
	}

	public ClientList(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cliente> getClientes(){
		return this.clientes;
	}
}
