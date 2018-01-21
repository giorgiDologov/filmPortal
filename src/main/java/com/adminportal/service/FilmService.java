package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Film;

public interface FilmService {
	
	Film save(Film book);

	List<Film> findAll();
	
	Film findById(int id);

	void delete(Film book);

}
