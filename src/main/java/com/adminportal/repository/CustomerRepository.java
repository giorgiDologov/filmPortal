package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findById(int id);

}
