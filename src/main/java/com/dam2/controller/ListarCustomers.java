package com.dam2.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dam2.model.entitys.Customer;
import com.dam2.model.service.ICustomerService;

@Controller
@RequestMapping("/customers") 
public class ListarCustomers {
	
		//@Autowired 
		private ICustomerService service;

		// Inyección por constructor
		public ListarCustomers (ICustomerService service)
		{
			this.service = service;
			System.out.println("ejecutando constructor del contrlador");
			cargarDatos ();
		}
		
		private void cargarDatos ()
		{
			service.save(new Customer("Jack", "Bauer",LocalDate.now()));
	        service.save(new Customer("Chloe", "O'Brian",LocalDate.now()));
	        service.save(new Customer("Kim", "Bauer",LocalDate.now()));
	        service.save(new Customer("David", "Palmer",LocalDate.now()));
	        service.save(new Customer("Michelle", "Dessler",LocalDate.now()));
		}
		
		@GetMapping(value="todos")
		public ModelAndView getAllCustomer()
		{
			ModelAndView modeloYVista = new ModelAndView ();
			modeloYVista.setViewName("listar_template");
			
			List<Customer>  customers = service.findAll();
			
			modeloYVista.addObject("customers", customers);
			modeloYVista.addObject("titulo", "listado de los clientes");
			
			return modeloYVista;
			
			
		}
		
		@GetMapping("customer")
		public ModelAndView getCustomer (@RequestParam("id") String id)
		{
			ModelAndView modeloYVista = new ModelAndView ();
			modeloYVista.setViewName("listar_uno_template");
			
			Customer  customer = service.findById(Long.valueOf(id));
			
			System.out.println(customer);
			modeloYVista.addObject("customer", customer);
			
			return modeloYVista;
		}

}
