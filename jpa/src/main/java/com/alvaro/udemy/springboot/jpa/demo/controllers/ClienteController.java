package com.alvaro.udemy.springboot.jpa.demo.controllers;

import com.alvaro.udemy.springboot.jpa.demo.controllers.util.paginator.PageRender;
import com.alvaro.udemy.springboot.jpa.demo.entity.Cliente;
import com.alvaro.udemy.springboot.jpa.demo.service.ClienteService;
import com.alvaro.udemy.springboot.jpa.demo.service.UploadFileService;
import com.alvaro.udemy.springboot.jpa.demo.view.xml.ClientList;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private UploadFileService uploadFileService;
	private final Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = { "/listar", "/" }, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model, HttpServletRequest request, Locale locale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			logger.info("Hola usuario '" + auth.getName() + "'");
		}
		/**
		 * Funcion propia
		 */
		if (hasRole("ROLE_ADMIN"))
			logger.info("HasRole() Hola " + auth.getName() + " tienes acceso");
		else
			logger.info("HasRole() Hola " + auth.getName() + " NO tienes acceso");
		/**
		 * Funcion SecurityContextHolderAwareRequestWrapper
		 */
		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");
		if (securityContext.isUserInRole("ADMIN"))
			logger.info("SecurityContextHolderAwareRequestWrapper Hola " + auth.getName() + " tienes acceso");
		else
			logger.info("SecurityContextHolderAwareRequestWrapper Hola " + auth.getName() + " NO tienes acceso");
		/**
		 * Utilizando el request
		 */
		if (request.isUserInRole("ROLE_ADMIN"))
			logger.info("request.isUserInRole Hola " + auth.getName() + " tienes acceso");
		else
			logger.info("request.isUserInRole Hola " + auth.getName() + " NO tienes acceso");
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		model.addAttribute("titulo", messageSource.getMessage("text.cliente.listar.titulo", null, locale));
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash, Locale locale) {
		Cliente cliente = clienteService.findOne(id);
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", messageSource.getMessage("text.client.ver.titulo" + cliente.getNombre(), null, locale));
		return "ver";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		model.put("titulo", "Nuevo Cliente");
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		return "form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		if (!foto.isEmpty()) {
			if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null && cliente.getFoto().length() > 0) {
				uploadFileService.delete(cliente.getFoto());
			}
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}
			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
			cliente.setFoto(uniqueFilename);
		}
		String mensajeFlash = (cliente.getId() != null) ? "Cliente editado con éxito!" : "Cliente creado con éxito!";
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente;
		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El cliente no existe");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "ID del cliente incorrecto");
			return "redirect:/listar";
		}
		model.put("titulo", "Editar Cliente");
		model.put("cliente", cliente);
		return "form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Cliente cliente = clienteService.findOne(id);
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con exito");
			if (uploadFileService.delete(cliente.getFoto()))
				flash.addFlashAttribute("info", "Foto" + cliente.getFoto() + " eliminada correctamente");
		}
		return "redirect:/listar";
	}

	private boolean hasRole(String role) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null)
			return false;
		return auth.getAuthorities().contains(new SimpleGrantedAuthority(role));
	}
}
