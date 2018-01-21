package com.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Film;
import com.adminportal.domain.Rental;
import com.adminportal.domain.Customer;

public interface RentalRepository extends CrudRepository<Rental, Long> {
	
	void delete(Rental rental);

	List<Rental> findByFilm(Film film);

	Rental findById(int rentalId);

	List<Rental> findByCustomer(Customer customer);

}
