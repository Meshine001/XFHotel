package com.xfhotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_price")
public class Price {
	@Id
	@GeneratedValue(generator="facilitygenerator")
	@GenericGenerator(name="facilitygenerator",strategy="increment")
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Apartment apartment;
	
	private long date;
	private float price;
	
	public Price(Apartment apartment, long date, float price) {
		super();
		this.apartment = apartment;
		this.date = date;
		this.price = price;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Apartment getApartment() {
		return apartment;
	}
	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
