package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Customer;

public interface CustomerService {

	void save(Customer customer);

	Customer findById(int id);

	void delete(Customer customer);

	List<Customer> findAll();

}
