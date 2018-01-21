package com.adminportal.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;


@Entity
@Table(name="rental_table")
public class Rental {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String rentOut;
	private String finalReturn;
	private String estimatedReturn;
	@OneToOne
	private Price price;
	private boolean returned = false;
	
	@OneToOne
	private Film film;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getRentOut() {
		return rentOut;
	}

	public void setRentOut(String rentOut) {
		this.rentOut = rentOut;
	}

	public String getFinalReturn() {
		return finalReturn;
	}

	public void setFinalReturn(String finalReturn) {
		this.finalReturn = finalReturn;
	}

	public String getEstimatedReturn() {
		return estimatedReturn;
	}

	public void setEstimatedReturn(String estimatedReturn) {
		this.estimatedReturn = estimatedReturn;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

}
