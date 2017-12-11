package com.adminportal.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Film;
import com.adminportal.domain.Rental;
import com.adminportal.domain.Customer;
import com.adminportal.repository.RentalRepository;
import com.adminportal.repository.CustomerRepository;
import com.adminportal.service.FilmService;
import com.adminportal.service.RentalService;

@Service
public class RentalServiceImpl implements RentalService {
	
	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private FilmService filmService;

	@Override
	public synchronized boolean createRental(int customerId, Film film, int days) {
		System.out.println(film.getInStockNumber());
		if(film.getInStockNumber()>0) {
		
		Customer customer = customerRepository.findById(customerId);
		
		Rental rental = new Rental();
		rental.setCustomer(customer);
		rental.setFilm(film);
		
		//today to DB String
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // today
		String today = sdf.format(c.getTime());
		rental.setRentOut(today);
		
		//today plus rental time to DB String
		c.add(Calendar.DATE, days); // today + days
		String plusDays = sdf.format(c.getTime());
		rental.setEstimatedReturn(plusDays);
		
		//SET PRICE
		if(film.getFilmStatus() == Film.Status.NEW) {
			
			rental.setEstimatedPrice(40 * days);
			customer.setBonusPoints(customer.getBonusPoints()+2);
			
		} else if(film.getFilmStatus() == Film.Status.REGULAR) {
			
			if(days>3) {
				
				rental.setEstimatedPrice(30 + (days - 3) * 30);
				customer.setBonusPoints(customer.getBonusPoints()+1);
				
			} else {
				
				rental.setEstimatedPrice(30);
				customer.setBonusPoints(customer.getBonusPoints()+1);
			}
			
		} else if(film.getFilmStatus() == Film.Status.OLD) {
			
			if(days>5) {
				
				rental.setEstimatedPrice(30 + (days - 5) * 30);
				customer.setBonusPoints(customer.getBonusPoints()+1);
				
			} else {
				
				rental.setEstimatedPrice(30);
				customer.setBonusPoints(customer.getBonusPoints()+1);
				
			}
			
		}
		
		
		//SAVE OBJECTS: 
		
			//RENTAL TO DB
		save(rental);
			
			//RENTAL TO USER
		customer.getRentals().add(rental);
		
		//FILM DECREASES IN STOCK, INCREASES RENTED OUT
		film.setInStockNumber(film.getInStockNumber()-1);
		
		film.setRentedOut(film.getRentedOut()+1);
		
		filmService.save(film);
		
		return true;
		
		}
		
		return false;
	}

	@Override
	public void save(Rental rental) {
		rentalRepository.save(rental);
	}

	@Override
	public synchronized void deleteRental(int rentalId) {
		Rental toDeleteRental = rentalRepository.findById(rentalId);
		
		//DELETE FROM USER
				toDeleteRental.getCustomer().getRentals().remove(toDeleteRental);
		
				
		//IF ONCE FINISHED RENTAL -> STOCK NUMBER RETURNED, NO NEED FOR FURTHER CHANGE	
		if(toDeleteRental.isReturned()==false) {
		//MODIFY FILM STOCK NUMBERS
		Film film = toDeleteRental.getFilm();
		
		film.setInStockNumber(film.getInStockNumber()+1);
		
		film.setRentedOut(film.getRentedOut()-1);
		
		filmService.save(film);
		}
		
		//DELETE
		rentalRepository.delete(toDeleteRental);
		
	}

	@Override
	public List<Rental> findAll() {
		return (List<Rental>) rentalRepository.findAll();
	}

	@Override
	public synchronized void finishRental(int rentalId){
		Rental finishRental = rentalRepository.findById(rentalId);
		
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		//converts DB rent out date from String to Date
		Date rentOutDate;
		try {
			rentOutDate = format.parse(finishRental.getRentOut());
		} catch (ParseException e) {
			rentOutDate = new Date();
			e.printStackTrace();
		}
		
		//today to DB String
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // today
		String today = sdf.format(c.getTime());
		finishRental.setFinalReturn(today);
		
		//today from String to Date (does after saving it to final return)
		Date finalReturn;
		try {
			finalReturn = format.parse(finishRental.getRentOut());
		} catch (ParseException e) {
			finalReturn = new Date();
			e.printStackTrace();
		}
		//REAL RENTAL TIME
		long diff = finalReturn.getTime() - rentOutDate.getTime();
		if(diff<1) {
			diff = 1;
		}
		
		//SET PRICE
		if(finishRental.getFilm().getFilmStatus() == Film.Status.NEW) {
					
					finishRental.setFinalPrice(40 * (int) diff);
					
				} else if(finishRental.getFilm().getFilmStatus() == Film.Status.REGULAR) {
					
					if((int) diff > 3) {
						
						finishRental.setFinalPrice(30 + ((int) diff - 3) * 30);
						
					} else {
						
						finishRental.setFinalPrice(30);
						
					}
					
				} else if(finishRental.getFilm().getFilmStatus() == Film.Status.OLD) {
					
					if((int) diff > 5) {
						
						finishRental.setFinalPrice(30 + ((int) diff - 5) * 30);
						
					} else {
						
						finishRental.setFinalPrice(30);
						
					}
					
		}
				
		//RENTAL TO DB & SET RETURNED
		finishRental.setReturned(true);
		save(finishRental);
				
		//MODIFY FILM STOCK NUMBERS
		Film film = finishRental.getFilm();
				
		film.setInStockNumber(film.getInStockNumber()+1);
				
		film.setRentedOut(film.getRentedOut()-1);
		
		filmService.save(film);
		
	}

	@Override
	public Rental findById(int id) {
		return rentalRepository.findById(id);
	}

	@Override
	public List<Rental> findByCustomer(Customer customer) {
		return rentalRepository.findByCustomer(customer);
	}

}
