package com.xfhotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_price")
public class Price {
	@Id
	private long id;
	private double price;
	@ManyToOne
	private LeaseType leasetype;
	@ManyToOne
	private Apartment apartment;
	@ManyToOne
	private Room room;
	
	public Price(double price, LeaseType leasetype, Apartment apartment, Room room) {
		super();
		this.price = price;
		this.leasetype = leasetype;
		this.apartment = apartment;
		this.room = room;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public LeaseType getLeasetype() {
		return leasetype;
	}
	public void setLeasetype(LeaseType leasetype) {
		this.leasetype = leasetype;
	}
	public Apartment getApartment() {
		return apartment;
	}
	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
}
