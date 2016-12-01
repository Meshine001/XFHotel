package com.xfhotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@JoinColumn(name="roomId")
	private Apartment apartment;
	
	private double Square;//面积
	private String capacity;//人数
	private String direction;//房间朝向
	private String description;//名称
	
	public Room(Apartment apartment, double square, String capacity, String direction, String description, int payway,
			int price) {
		super();
		this.apartment = apartment;
		Square = square;
		this.capacity = capacity;
		this.direction = direction;
		this.description = description;
		this.payway = payway;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	private int payway;//支付方式
	private int price;//价格基准
	//private Set<Files> pictures;//参考图片
	

	public String getCapacity() {
		return capacity;
	}

	public double getSquare() {
		return Square;
	}

	public void setSquare(double square) {
		Square = square;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public int getPayway() {
		return payway;
	}

	public void setPayway(int payway) {
		this.payway = payway;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
