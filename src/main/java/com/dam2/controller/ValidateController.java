package com.dam2.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dam2.model.entitys.Customer;
import com.dam2.model.service.ICustomerService;

@Controller
@RequestMapping("/validate")
public class ValidateController {
	@Autowired ICustomerService daoCustomers;
	@GetMapping("login")
	public ModelAndView validateDate(@RequestParam String name, @RequestParam String dateBirth, HttpSession sesion) {
		ModelAndView modeloYVista = new ModelAndView();
		
		LocalDate fechaNac = LocalDate.parse(dateBirth);
		// TODO Asegurar que es mayor de edad correctamente
		if (fechaNac.minusYears(18)
				.isBefore(LocalDate.now().minusYears(18))) {
			
			modeloYVista.setViewName("form_last_name");
			sesion.setAttribute("name", name);
			sesion.setAttribute("dateBirth", fechaNac);
			sesion.setMaxInactiveInterval(300);
		}else {
			modeloYVista.setViewName("login_fail");
		}
		
		
		return modeloYVista;
	}
	
	@GetMapping("getlastnames")
	public ModelAndView getPorLastname(@RequestParam String lastname, HttpSession sesion) {
		ModelAndView modeloYVista = new ModelAndView();
		
		modeloYVista.setViewName("list_lastname");
		
		modeloYVista.addObject("header_lastname", lastname);
		modeloYVista.addObject("list_lastname", daoCustomers.findByLastName(lastname));
		sesion.invalidate();
		return modeloYVista;
		
	}

}
