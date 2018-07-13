package com.alvaro.udemy.springboot.jpa.demo.view.xml;

import com.alvaro.udemy.springboot.jpa.demo.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("listar.xml")
public class ClienteListXMLView extends MarshallingView {

	@Autowired
	public ClienteListXMLView(Jaxb2Marshaller marshaller) {
		super(marshaller);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		model.remove("titulo");
		model.remove("page");

		Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");

		model.remove("clientes");
		model.put("clienteList", new ClientList(clientes.getContent()));

		super.renderMergedOutputModel(model, request, response);
	}

}
