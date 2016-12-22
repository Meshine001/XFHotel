package com.xfhotel.hotel.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_room")
public class Room {
	// apartment
	// person
	// informationi
	// facilities
	// pictures
	@Id
	@GeneratedValue(generator = "apartmentgenerator")
	@GenericGenerator(name = "apartmentgenerator", strategy = "increment")
	private long id;

	@ManyToOne
	private Apartment apartment;

	public String prices;//
	
	@ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "t_room_facility", joinColumns = { @JoinColumn(name = "room_id") }, inverseJoinColumns = {
			@JoinColumn(name = "facility_id") })
	public Set<Facility> facilities; //

	private double square;//
	private String direction;//
	private String capacity;//
	private String description;//

	private String pics;

	public Room() {
		// TODO Auto-generated constructor stub
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
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


	public String getPrices() {
		return prices;
	}

	public void setPrices(String prices) {
		this.prices = prices;
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

	public Set<Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(Set<Facility> facilities) {
		this.facilities = facilities;
	}

	public Map toMap() {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("square", square);
		map.put("direction", direction);
		map.put("capacity", capacity);
		map.put("description", description.split("@")[0]);
		map.put("type", description.split("@")[1]);
		map.put("ltype", description.split("@")[2]);
		map.put("id", id);
		if(pics != null){
			map.put("pics", pics.split("@"));
		}
		map.put("apartment", apartment.toMap());
		map.put("prices", prices.split("@"));
		return map;
	}

}
