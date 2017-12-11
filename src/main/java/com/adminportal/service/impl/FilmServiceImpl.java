package com.adminportal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Film;
import com.adminportal.domain.Rental;
import com.adminportal.repository.FilmRepository;
import com.adminportal.repository.RentalRepository;
import com.adminportal.service.FilmService;
import com.adminportal.service.RentalService;

@Service
public class FilmServiceImpl implements FilmService {
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private RentalService rentalService;
	
	public Film save(Film film) {
		
		return filmRepository.save(film);
	}
	
	public List<Film> findAll(){
		return (List<Film>) filmRepository.findAll();
	}

	@Override
	public Film findById(int id) {
		
		return filmRepository.findById(id);
	}

	@Override
	public synchronized void delete(Film film) {
		
		//AVOIDING RENTAL LIST BECOME NULL MEANWHILE LOOPING 
		List<Rental> rentalList = new ArrayList<>();
		if(rentalRepository.findByFilm(film)!=null) {
			for(Rental rental: rentalRepository.findByFilm(film)) {
				rentalList.add(rental);
			}
		} 
		
		//DELETE FILM'S RENTALS
		if(rentalList!=null) {
		for(Rental rental: rentalList) {
			rentalService.deleteRental(rental.getId());
		}
		}
		
		filmRepository.delete(film);
		
			
		}
		
		
		
	}



