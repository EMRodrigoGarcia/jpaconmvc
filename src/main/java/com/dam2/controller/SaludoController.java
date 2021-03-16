package com.dam2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/saludar") 
public class SaludoController {
	
	
	@GetMapping("hola")
	public ModelAndView saludar() 
	{
		ModelAndView modeloYVista = new ModelAndView ();
		modeloYVista.setViewName("saludar_template");
		
		modeloYVista.addObject("name", "Customer");
		
		return modeloYVista;
		
	}
	
	@GetMapping("persona")
	public ModelAndView saludarPersona(@RequestParam("nombre") String nombre, HttpSession sesion) 
	{
		
		sesion.setAttribute("nombre", nombre);
		
		sesion.setMaxInactiveInterval(180);
		ModelAndView modeloYVista = new ModelAndView ();
		modeloYVista.setViewName("pediredad");
		
		modeloYVista.addObject("name", nombre);
		
		return modeloYVista;
		
	}
	
	@GetMapping("edad")
	public ModelAndView saludarPersonaEdad(@RequestParam("edad") String edad, HttpSession sesion) 
	{
		String nombre = (String) sesion.getAttribute("nombre");
		
		ModelAndView modeloYVista = new ModelAndView ();
		modeloYVista.setViewName("saludar_edad_template");
		
		modeloYVista.addObject("edad", edad);
		modeloYVista.addObject("nombre",nombre);
		sesion.invalidate();
		return modeloYVista;
		
	}

}
