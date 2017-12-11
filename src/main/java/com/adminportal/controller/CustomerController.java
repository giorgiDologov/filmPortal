package com.adminportal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adminportal.domain.Customer;
import com.adminportal.service.CustomerService;
import com.adminportal.service.UserService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private CustomerService customerService;

	
	@RequestMapping(value="/addCustomer" , method=RequestMethod.GET)
	public String addCustomer(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer",customer);
		return "addCustomer";
	}
	
	@RequestMapping(value="/addCustomer" , method=RequestMethod.POST)
	public String addCustomerPost(
								@ModelAttribute("book") Customer customer,
								HttpServletRequest request) {
		
		customerService.save(customer);
		
		return "redirect:/customer/customerList";
	}
	
	@RequestMapping(value="/updateCustomer", method=RequestMethod.GET)
	public String updateCustomerGET(@PathParam("id") int id ,Model model) {
		Customer customer = customerService.findById(id);
		model.addAttribute("customer", customer);
		return "updateCustomer";
	}
	
	@RequestMapping(value="/updateCustomer", method=RequestMethod.POST)
	public String updateCustomerPOST(@ModelAttribute("customer") Customer customer, HttpServletRequest request) {
		customerService.save(customer);
		return "redirect:/customer/customerList";
	}
	
	@RequestMapping(value="/deleteCustomer")
	public String deleteCustomer(@PathParam("id") int id, Model model) {
		
		Customer customer = customerService.findById(id);
		
		if(customer!=null) {
			
		customerService.delete(customer);
		
		}
		
		return "redirect:/customer/customerList";
	}
	
	@RequestMapping("/customerList")
	public String customerList(Model model) {
		List<Customer> customerList = customerService.findAll();
		if(customerList==null || customerList.size()==0) {
			model.addAttribute("isCustomer", false);
		} else {
		model.addAttribute("customerList", customerList);
		model.addAttribute("isCustomer", true);
		}
		return "customerList";
	}
	

}
