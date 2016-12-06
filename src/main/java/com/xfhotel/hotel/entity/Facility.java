package com.xfhotel.hotel.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_facility")
public class Facility {
	@Id
	private long id;
	private String description;
	@ManyToMany(fetch = FetchType.LAZY,mappedBy="facilities")
	public Set<Apartment> apartments;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Apartment> getApartments() {
		return apartments;
	}
	public void setApartments(Set<Apartment> apartments) {
		this.apartments = apartments;
	}

}
