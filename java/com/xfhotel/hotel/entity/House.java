package com.xfhotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_house")
public class House {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long date;
	private int state;
	private Long apartmentId;
	
	public House() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Long getApartmentId() {
		return apartmentId;
	}
	public void setApartmentId(Long apartmentId) {
		this.apartmentId = apartmentId;
	}
	
	@Override
	public String toString() {
		return "House [id=" + id + ", date=" + date + ", apartmentId=" + apartmentId + ", state=" + state + "]";
	}
	
	

}
