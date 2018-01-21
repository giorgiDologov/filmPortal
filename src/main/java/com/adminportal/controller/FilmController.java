package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Film;
import com.adminportal.domain.Price;
import com.adminportal.service.FilmService;
import com.adminportal.service.UserService;

@Controller
@RequestMapping("/film")
public class FilmController {
	
	@Autowired
	private FilmService filmService;
	
	@RequestMapping(value="/add" , method=RequestMethod.GET)
	public String addFilm(Model model) {
		Film film = new Film();
		model.addAttribute("film",film);
		model.addAttribute("status", new String());
		return "addFilm";
	}
	
	@RequestMapping(value="/add" , method=RequestMethod.POST)
	public String addFilmPost(
								@ModelAttribute("film") Film film,
								@ModelAttribute("image") MultipartFile image,
								@ModelAttribute("status") String status,
								HttpServletRequest request) {
		
		
		try {
			
			byte[] bytes = film.getImage().getBytes();
		
			if(bytes.length > 0) {
				film.setFilmImage(bytes);
			}
			
			switch(status) {
			
				case "NEW": film.setStatus(Film.Status.NEW);
							break;
							
				case "REGULAR": film.setStatus(Film.Status.REGULAR);
								break;
								
				case "OLD": film.setStatus(Film.Status.OLD);
							break;
				default: break;				
			}
		
		} catch (Exception e) {
			
			return "redirect:/film/add";
			
		}
		
		filmService.save(film);
		
		
		return "redirect:/film/filmList";
	}
	
	@RequestMapping("/filmList")
	public String filmList(Model model) {
		List<Film> filmList = filmService.findAll();
		model.addAttribute("filmList", filmList);
		return "filmList";
	}
	
	@Transactional
	@RequestMapping(value="/updateFilm", method=RequestMethod.GET)
	public String updateFilmGET(@PathParam("id") int id ,Model model) {
		Film film = filmService.findById(id);
		model.addAttribute("film", film);
		model.addAttribute("status", new String());
		return "updateFilm";
	}
	
	@Transactional
	@RequestMapping(value="/updateFilm", method=RequestMethod.POST)
	public String updateFilmPOST(@ModelAttribute("film") Film film, @ModelAttribute("status") String status) {

		try {
			byte[] bytes = film.getImage().getBytes();
			film.setFilmImage(bytes);
			
			//writing new image to folder <- just for fun
			String name = film.getId() + ".png";
			BufferedOutputStream buffer = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/" + name)));
			buffer.write(bytes);
			buffer.close();
			
			switch(status) {
			
				case "NEW": film.setStatus(Film.Status.NEW);
							break;
						
				case "REGULAR": film.setStatus(Film.Status.REGULAR);
								break;
							
				case "OLD": film.setStatus(Film.Status.OLD);
							break;
							
				default: break;				
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		filmService.save(film);
		
		
		return "redirect:/film/filmList";
	}
	
	@RequestMapping(value="/filmInfo")
	public String filmInfo(@RequestParam("id") int id, Model model) {
		Film film = filmService.findById(id);
		model.addAttribute("film", film);
		return "filmInfo";
	}
	
	@RequestMapping(value="/delete")
	public String delete(@PathParam("id") int id, Model model) {
		
		Film film = filmService.findById(id);
		
		if(film!=null) {
			
		filmService.delete(film);
		
		}
		
		return "redirect:/film/filmList";
	}
	
	

}
