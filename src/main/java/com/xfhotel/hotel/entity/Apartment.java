package com.xfhotel.hotel.entity;

import java.util.HashMap;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name = "t_apartment")
public class Apartment {
	//Position
	//facilities
	//map
	@Id
	@GeneratedValue(generator="apartmentgenerator")
	@GenericGenerator(name="apartmentgenerator",strategy="increment")
	private long id;
	//coordinate  鍦板潃缁忕含搴�
	private double latitude; //for map
	private double longitude;
	
	@OneToMany
	@JoinColumn(name="apartment_id")
	public Set<Room> rooms; //
	
	//璁炬柦
	@ManyToMany(cascade={CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinTable(name="t_apartment_facility",
		joinColumns={@JoinColumn(name="apartment_id")},
		inverseJoinColumns={@JoinColumn(name="facility_id")})
	public Set<Facility> facilities; //
	
	//鐗硅壊
	@ManyToMany(cascade={CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinTable(name="t_apartment_feature",
		joinColumns={@JoinColumn(name="apartment_id")},
		inverseJoinColumns={@JoinColumn(name="feature_id")})
	public Set<Feature> features;//label for apartment
	
	
	@OneToMany
	@JoinColumn(name="apartment_id")
	public Set<Price> prices;//
	
	//閰掑簵鍨嬫垨鐭鍨�
	@ManyToOne
	private ApartmentType apartmentType;
	
	//鍚堢锛屾暣绉�
	private String type; //together,sigle
	
	private String address;//address : cell : num_building
	
	private String floor;//current floor : total floor
	
	//鏈濆悜
	private String direction;//
	//闈㈢Н
	private double square;//square for apartment
	//鍙綇鍑犱汉
	private String capacity;//num of people
	//甯冨眬
	private String layout;// bedroom : livingroom : bathroom : balcony
	
	
	private String description;//enviroment,transportation,etc.

	private String layoutPic;
//	private Set<String> pics;//

	public Apartment() {
		// TODO Auto-generated constructor stub
	}

	public ApartmentType getApartmentType() {
		return apartmentType;
	}

	public void setApartmentType(ApartmentType apartmentType) {
		this.apartmentType = apartmentType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
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

	public String getLayoutPic() {
		return layoutPic;
	}

	public void setLayoutPic(String layoutPic) {
		this.layoutPic = layoutPic;
	}
	
	

}
