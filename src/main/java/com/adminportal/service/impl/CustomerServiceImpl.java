package com.adminportal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Customer;
import com.adminportal.domain.Rental;
import com.adminportal.repository.CustomerRepository;
import com.adminportal.service.CustomerService;
import com.adminportal.service.RentalService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RentalService rentalService;

	@Override
	public void save(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public Customer findById(int id) {
		return customerRepository.findById(id);
	}

	@Override
	public synchronized void delete(Customer customer) {
		
		//AVOIDING RENTAL LIST BECOME NULL MEANWHILE LOOPING 
		List<Rental> rentalList = new ArrayList<>();
		if(customer.getRentals()!=null) {
			for(Rental rental: customer.getRentals()) {
				rentalList.add(rental);
			}
		}
		
		//DELETE CUSTOMER'S RENTALS
		if(rentalList!=null) {
		for(Rental rental: rentalList) {
			rentalService.deleteRental(rental.getId());
		}
		}
		
		customerRepository.delete(customer);
	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) customerRepository.findAll();
	}

}
