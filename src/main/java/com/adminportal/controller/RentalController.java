package com.adminportal.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adminportal.domain.Film;
import com.adminportal.domain.Rental;
import com.adminportal.domain.Customer;
import com.adminportal.service.FilmService;
import com.adminportal.service.RentalService;
import com.adminportal.service.CustomerService;

@Controller
public class RentalController {
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping(value="/rentOut", method=RequestMethod.GET)
	public String rentOut(@RequestParam("id") int id, Model model) {
		Film film = filmService.findById(id);
		model.addAttribute("film", film);
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		return "rental";
	}
	
	@RequestMapping(value="/rentOut", method=RequestMethod.POST)
	public String rentOutPOST(@ModelAttribute("customerId") int id,
							  @ModelAttribute("days") int days,
							  @ModelAttribute("filmId") int filmId,
							  Model model) {
		
		Film film = filmService.findById(filmId);
		
		if(!rentalService.createRental(id, film, days)) {
			return "redirect:/rentOut?id=" + film.getId();
		} 
		
		return "redirect:/rentalList";
	}
	
	@RequestMapping("/rentalList")
	public String rentalList(Model model) {
		
		if(!model.containsAttribute("isRental")) {
			
			List<Rental> rentalList = rentalService.findAll();
			
				if(rentalList==null || rentalList.size()==0) {
					model.addAttribute("isRental", false);
				} else {
					model.addAttribute("rentalList", rentalList);
					model.addAttribute("isRental", true);
				}
				
		}
		return "rentList";
	}
	
	@RequestMapping(value="/deleteRental")
	public String deleteRental(@PathParam("id") int id, Model model) {
		
			rentalService.deleteRental(id);
		
		return "redirect:/rentalList";
	}
	
	@RequestMapping(value="/finishRental")
	public String finishRental(@PathParam("id") int id, Model model) {
		
			rentalService.finishRental(id);
		
		return "redirect:/rentalList";
	}
	
	@RequestMapping("/customerRental")
	public String finishRental(@PathParam("id") int id, Model model, RedirectAttributes attr) {
		List<Rental> rentalList = rentalService.findByCustomer(customerService.findById(id));
		
		if(rentalList==null || rentalList.size()==0) {
			attr.addFlashAttribute("isRental", false);
			attr.addFlashAttribute("message","There is no rental for " + customerService.findById(id).getFirstName() 
									+ " " + customerService.findById(id).getLastName());
		} else {
			attr.addFlashAttribute("rentalList", rentalList);
			attr.addFlashAttribute("isRental", true);
			attr.addFlashAttribute("message","Rentals of " + customerService.findById(id).getFirstName() + " " +
									customerService.findById(id).getLastName());
		}
		
		return "redirect:/rentalList";
	}

}
