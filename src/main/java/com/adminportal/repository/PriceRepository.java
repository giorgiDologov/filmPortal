package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Price;

public interface PriceRepository extends CrudRepository<Price, Integer> {

}
