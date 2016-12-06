package com.xfhotel.hotel.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	@GeneratedValue(generator="apartmentgenerator")
	@GenericGenerator(name="apartmentgenerator",strategy="increment")
	private long id;
	//coordinate
	private long latitude; //for map
	private long longitude;
	@OneToMany
	@JoinColumn(name="apartmentId")
	public Set<Room> rooms; //
	@ManyToMany(cascade={CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinTable(name="t_apartment_facility",
		joinColumns={@JoinColumn(name="apartmentId")},
		inverseJoinColumns={@JoinColumn(name="facilityId")})
	public Set<Facility> facilities; //
	@OneToMany
	@JoinColumn(name="apartmentId")
	public Set<Feature> features;//label for apartment
	@OneToMany
	@JoinColumn(name="apartmentId")
	public Set<Price> prices;//
	
	private String type; // hotel ,short ;;;;together,sigle
	private String address;//address : cell : num_building
	private String floor;//current floor : total floor
	private String direction;//
	private double square;//square for apartment
	private String capacity;//num of people
	private String layout;// bedroom : livingroom : bathroom : balcony
	private String description;//enviroment,transportation,etc.

	//private Set<File> pics;//
	
	public Apartment(long latitude, long longitude, Set<Room> rooms, Set<Facility> facilities, Set<Feature> features,
			Set<Price> prices, String type, String address, String floor, String direction, double square,
			String capacity, String layout, String description) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.rooms = rooms;
		this.facilities = facilities;
		this.features = features;
		this.prices = prices;
		this.type = type;
		this.address = address;
		this.floor = floor;
		this.direction = direction;
		this.square = square;
		this.capacity = capacity;
		this.layout = layout;
		this.description = description;
	}

	public Apartment() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	public Set<Price> getPrices() {
		return prices;
	}

	public void setPrices(Set<Price> prices) {
		this.prices = prices;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public double getSquare() {
		return square;
	}

	public void setSquare(double square) {
		this.square = square;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
