package com.adminportal.domain;

import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Film {
	
	private static final FetchType LAZY = null;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String title;
	private String author;
	private String publicationDate;
	private String language;
	private String category;
	
	public enum Status{
		NEW,
		REGULAR,
		OLD,
	}
	
	private Status filmStatus;
	
	@Column(columnDefinition="text")
	private String description;
	private int inStockNumber;
	private int rentedOut;
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="film_image")
	private byte[] filmImage;
	@Transient
	private MultipartFile image;
	@Transient
	private int[] qtyList;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getInStockNumber() {
		return inStockNumber;
	}
	public void setInStockNumber(int inStockNumber) {
		this.inStockNumber = inStockNumber;
	}
	public byte[] getFilmImage() {
		return filmImage;
	}
	public void setFilmImage(byte[] image) {
		this.filmImage = image;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public int[] getQtyList() {
		return qtyList;
	}
	public void setQtyList() {
		int[] array = new int[this.inStockNumber];
		Arrays.setAll(array, i -> i + 1);
		this.qtyList = array;
	}
	public Status getFilmStatus() {
		return filmStatus;
	}
	public void setFilmStatus(Status filmStatus) {
		this.filmStatus = filmStatus;
	}
	public void setQtyList(int[] qtyList) {
		this.qtyList = qtyList;
	}
	public int getRentedOut() {
		return rentedOut;
	}
	public void setRentedOut(int rentedOut) {
		this.rentedOut = rentedOut;
	}

}
