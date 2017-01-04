package com.xfhotel.hotel.entity;

import java.io.Serializable;
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

import com.xfhotel.hotel.common.Constants;

@Entity
@Table(name = "t_room")
public class Room implements Serializable{
	// apartment
	// person
	// informationi
	// facilities
	// pictures
	@Id
	@GeneratedValue(generator = "apartmentgenerator")
	@GenericGenerator(name = "apartmentgenerator", strategy = "increment")
	private long id;

	@ManyToOne(fetch=FetchType.LAZY)
	private Apartment apartment;
	
	private String status;
	
	public String prices;//
	public String price_scope;

	public String facilities; //

	private double square;//
	private String direction;//
	private String capacity;//
	private String description;//

	private String pics;

	public Room() {
		// TODO Auto-generated constructor stub
	}

	
	
	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
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
		int tmp = Integer.valueOf(prices.split("@")[0]);
		for(int i=0; i<Constants.price_scope.length;i++){
			if(tmp<Constants.price_scope[i]){
				this.price_scope=i+"";
			}
		}
		this.price_scope=Constants.price_scope.length+"";
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

	public String[] getFacilities() {
		return facilities.substring(1).split("@");
	}

	public String getPrice_scope() {
		return price_scope;
	}

	public void setPrice_scope(String price_scope) {
		this.price_scope = price_scope;
	}

	public void setFacilities(String[] facilities) {
		if(facilities==null){
			this.facilities="";
			return;
		}
		String str="";
		for (int i = 0; i < facilities.length; i++) {
			str = str + "@";
			str = str + facilities[i];
		}
		this.facilities=str;
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
		map.put("facilities", this.facilities.split("@"));
		map.put("apartment", apartment.getId());
		map.put("prices", prices.split("@"));
		map.put("status", status);
		return map;
	}

}
