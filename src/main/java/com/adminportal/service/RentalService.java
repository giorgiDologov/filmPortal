package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Film;
import com.adminportal.domain.Rental;
import com.adminportal.domain.Customer;

public interface RentalService {
	
	boolean createRental(int customerId, Film book, int days);
	
	void save(Rental cartItem);
	
	void deleteRental(int rentalId);
	
	void finishRental(int rentalId);

	List<Rental> findAll();

	Rental findById(int id);

	List<Rental> findByCustomer(Customer findById);

}
