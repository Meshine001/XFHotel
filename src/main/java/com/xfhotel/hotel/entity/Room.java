package com.xfhotel.hotel.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_room")
public class Room {
	//apartment
	//person
	//informationi
	//facilities
	//pictures
	@Id
	@GenericGenerator(name = "generator", strategy = "identity ")
	private long id;
	
	@ManyToOne
	private Apartment apartment;
	@OneToMany
	@JoinColumn(name="roomId")
	public Set<Price> prices;//
	
	private double Square;//
	private String direction;//
	private String capacity;//
	private String description;//
	
	public Room(Apartment apartment, Set<Price> prices, double square, String direction, String capacity,
			String description) {
		super();
		this.apartment = apartment;
		this.prices = prices;
		Square = square;
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
	public Set<Price> getPrices() {
		return prices;
	}
	public void setPrices(Set<Price> prices) {
		this.prices = prices;
	}
	public double getSquare() {
		return Square;
	}
	public void setSquare(double square) {
		Square = square;
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
