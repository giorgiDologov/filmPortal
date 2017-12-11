package com.adminportal.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Rental {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String rentOut;
	private String finalReturn;
	private String estimatedReturn;
	private int estimatedPrice;
	private int finalPrice;
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

	public int getEstimatedPrice() {
		return estimatedPrice;
	}

	public void setEstimatedPrice(int estimatedPrice) {
		this.estimatedPrice = estimatedPrice;
	}
	
	public int getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(int finalPrice) {
		this.finalPrice = finalPrice;
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

}
