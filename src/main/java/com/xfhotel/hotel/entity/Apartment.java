package com.xfhotel.hotel.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_apartment")
public class Apartment {
	//Position
	//facilities
	//map
	@Id
	@GenericGenerator(name = "generator", strategy = "identity ")
	private long id;
	@OneToMany
	@JoinColumn(name="apartmentId")
	public Set<Room> rooms;
	@ManyToMany
	@JoinColumn(name="apartmentId")
	public Set<Facility> facilities;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Set<Room> getRooms() {
		return rooms;
	}
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
	public Set<Facility> getFacilities() {
		return facilities;
	}
	public void setFacilities(Set<Facility> facilities) {
		this.facilities = facilities;
	}
	
}
