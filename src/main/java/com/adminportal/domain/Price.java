package com.adminportal.domain;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.TypeMismatchException;

@Entity
public class Price {
	
	//only getters, we set everything by constructor
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int price;
	
	private int days;
	
	private int finalPrice;
	
	@OneToOne
	private Rental rental;
	
	public Price() {
		
	}
	
	public Price(Film.Status status, int days) {
		
		this.days = days;
		
		switch(status) {
			case NEW: 
						this.price = 40;
						this.finalPrice = this.price * this.days;
						break;
			case REGULAR:
						this.price = 30;
						if(days > 3) {
							this.finalPrice = this.price + (this.days - 3) * this.price;
						} else {
							this.finalPrice = this.price;
						}
						break;
			case OLD: 
						this.price = 30;
						if(days > 5) {
							this.finalPrice = this.price + (this.days - 5) * this.price;
						} else {
							this.finalPrice = this.price;
						}
						break;
			default:
				throw new TypeMismatchException("Status not allowed: or New, or Regular, or Old");
			
		}
		
	}

	public int getDays() {
		return days;
	}

	public int getPrice() {
		return price;
	}

	public int getFinalPrice() {
		return finalPrice;
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}


}
