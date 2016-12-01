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
	//coordinate
	private long latitude;
	private long longitude;
	@OneToMany
	@JoinColumn(name="apartmentId")
	public Set<Room> rooms;
	@ManyToMany
	@JoinColumn(name="apartmentId")
	public Set<Facility> facilities;
	
	private String community;//小区
	private String floor;//楼层、总楼层
	private String direction;//朝向
	private String layout;//布局

	//private File layoutpic;//户型图片
	

	public Apartment(long latitude, long longitude, Set<Room> rooms, Set<Facility> facilities, String community,
			String floor, String direction, String layout) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.rooms = rooms;
		this.facilities = facilities;
		this.community = community;
		this.floor = floor;
		this.direction = direction;
		this.layout = layout;
	}
	
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
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
	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	public long getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	
}
