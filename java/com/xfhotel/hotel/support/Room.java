package com.xfhotel.hotel.support;

import java.util.List;
import java.util.Set;


import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Facility;

public class Room {
	
	private long id;

	private Apartment apartment;
	public Set<Facility> facilities; //

	private double square;//
	private String direction;//
	private String capacity;//
	private String description;//
	
	
	
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Room(long id, Apartment apartment,Set<Facility> facilities, double square,
			String direction, String capacity, String description) {
		super();
		this.id = id;
		this.apartment = apartment;
		this.facilities = facilities;
		this.square = square;
		this.direction = direction;
		this.capacity = capacity;
		this.description = description;
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

	public Set<Facility> getFacilities() {
		return facilities;
	}
	public void setFacilities(Set<Facility> facilities) {
		this.facilities = facilities;
	}
	public double getSquare() {
		return square;
	}
	public void setSquare(double square) {
		this.square = square;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
