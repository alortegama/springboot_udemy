package com.alvaro.udemy.springboot.jpa.demo.controllers;

import com.alvaro.udemy.springboot.jpa.demo.controllers.util.paginator.PageRender;
import com.alvaro.udemy.springboot.jpa.demo.entity.Cliente;
import com.alvaro.udemy.springboot.jpa.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("titulo", "Pagina Inicial");
        return "home";

    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page",defaultValue = "0") int page  ,Model model) {
        Pageable pageRequest = PageRequest.of(page,4);
        Page<Cliente> clientes = clienteService.findAll(pageRequest);

        PageRender<Cliente> pageRender = new PageRender<>("/listar",clientes);
        model.addAttribute("titulo", "Listado clientes");
        model.addAttribute("clientes", clientes);
        model.addAttribute("page",pageRender);
        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        model.put("titulo", "Nuevo Cliente");
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult bindingResult, Model model, RedirectAttributes flash, SessionStatus status) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Cliente");
            return "form";
        }
        clienteService.save(cliente);
        status.setComplete();
        flash.addFlashAttribute("success", "Cliente creado con exito");
        return "redirect:listar";
    }

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
        flash.addFlashAttribute("success", "Cliente editado con exito");

        return "form";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        Cliente cliente;
        if (id > 0) {
            clienteService.delete(id);
            flash.addFlashAttribute("success", "Cliente eliminado con exito");
        }
        return "redirect:/listar";
    }
}
