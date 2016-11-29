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
