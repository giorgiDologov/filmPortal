package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Film;

public interface FilmRepository extends CrudRepository<Film, Integer> {
	
	Film save(Film film);

	Film findById(int id);

}
