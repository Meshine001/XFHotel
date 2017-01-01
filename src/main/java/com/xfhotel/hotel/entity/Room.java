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

@Entity
@Table(name = "t_room")
public class Room implements Serializable {

	public static final int STATUS_ON_PAY = 0;
	public static final int STATUS_LEASED = 1;
	public static final int STATUS_IDLE = 2;
	public static final int STATUS_WILL_IDLE = 3;
	// apartment
	// person
	// informationi
	// facilities
	// pictures
	@Id
	@GeneratedValue(generator = "apartmentgenerator")
	@GenericGenerator(name = "apartmentgenerator", strategy = "increment")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Apartment apartment;

	private int status;

	public String prices;//

	public String facilities; //

	private double square;//
	private String direction;//
	private String capacity;//
	private String description;//

	private String pics;

	public Room() {
		// TODO Auto-generated constructor stub
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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
		return facilities.split("@");
	}

	public void setFacilities(String[] facilities) {
		if (facilities == null) {
			this.facilities = "";
			return;
		}
		String str = "";
		for (int i = 0; i < facilities.length; i++) {
			if (i > 0)
				str = str + "@";
			str = str + facilities[i];
		}
		this.facilities = str;
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
		if (pics != null) {
			map.put("pics", pics.split("@"));
		}
		map.put("facilities", this.facilities.split("@"));
		map.put("apartment", apartment.getId());
		map.put("prices", prices.split("@"));
		map.put("status", getStatusString(status));
		return map;
	}

	String getStatusString(int s) {
		switch (s) {
		case STATUS_ON_PAY:
			return "正在支付";
		case STATUS_LEASED:
			return "已出租";
		case STATUS_WILL_IDLE:
			return "可能续租";
		default:
			return "未出租";

		}
	}

}
