package com.SpringBoot.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.SpringBoot.app.models.entity.Cliente;
import com.SpringBoot.app.models.service.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	IClienteService iclienteService;
	
	
	@GetMapping("/listar")
	public String listarClientes(Model modelo) {
		modelo.addAttribute("titulo","lista de clientes");
		modelo.addAttribute("clientes",iclienteService.findAll());
		return "listar";
	}
	
	//	DDDDDDDDDDDDDDDDDDDDDDDDD
	@GetMapping("/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("titulo", "Registrar cliente");
		model.put("cliente", cliente);
		return "form";
	}


	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Recuerde llenar todos los campos!");
			return "form";
		}
		status.setComplete();
		iclienteService.save(cliente);
		return "redirect:listar";
	}
	
	
	@RequestMapping(value = "/form/{id}")
	public String leerCliente(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = iclienteService.findById(id);
		} else {
			return "redirect:/listar";
		}
		
		model.put("cliente",cliente);
		model.put("titulo", "Editar cliente");
		return "form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if (id > 0) {
			iclienteService.deleteById(id);;
		} 
		return "redirect:/listar";
	}

}
