package com.adminportal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adminportal.service.FilmService;

@Controller
public class HomeController {
	
	@Autowired
	private FilmService filmService;
	
	@RequestMapping("/")
	public String homePage() {
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/imageController/{id}")
	@ResponseBody
	public byte[] filmImage(HttpServletRequest request, @ModelAttribute("id") int id, Model model)  {
	  return filmService.findById(id).getFilmImage();
	}

}
