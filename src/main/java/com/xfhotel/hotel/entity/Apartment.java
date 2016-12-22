package com.xfhotel.hotel.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	
	
	public String prices;//day@weekend@month@year
	
	private String apartmentType; //hotel or apartment
	
	private String type; //together,sigle
	
	private String address;//address : cell : num_building
	
	private String floor;//current floor : total floor
	
	private String direction;//
	private double square;//square for apartment
	private String capacity;//num of people
	private String layout;// bedroom : livingroom : bathroom : balcony
	
	private String description;//enviroment,transportation,etc.

	private String pic1;//layoutPic
	private String pic2;//ke ting, can ting
	private String pic3;//bathroom chufang
	private String pic4;//xiao qu
	
	public Apartment() {
		// TODO Auto-generated constructor stub
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


	public String getPrices() {
		return prices;
	}


	public void setPrices(String prices) {
		this.prices = prices;
	}


	public String getApartmentType() {
		return apartmentType;
	}


	public void setApartmentType(String apartmentType) {
		this.apartmentType = apartmentType;
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




	public String getPic1() {
		return pic1;
	}


	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}


	public String getPic2() {
		return pic2;
	}


	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}


	public String getPic3() {
		return pic3;
	}


	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}


	public String getPic4() {
		return pic4;
	}


	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}


	public Map toMap(){
		Map map = new HashMap();
		map.put("id",this.getId());
		map.put("latitude",this.getLatitude());
		map.put("longitude",this.getLongitude());
		map.put("type",this.getType());
		map.put("address",this.getAddress().split("@")[0]);
		map.put("community",this.getAddress().split("@")[1]);
		map.put("num_building",this.getAddress().split("@")[2]);
		map.put("location",this.getAddress().split("@")[3]);
		map.put("floor",this.getFloor().split("@")[0]);
		map.put("totalfloor",this.getFloor().split("@")[1]);
		map.put("direction",this.getDirection());
		map.put("square",this.getSquare());
		map.put("capacity",this.getCapacity());
		map.put("bedroom",this.getLayout().split("@")[0]);
		map.put("livingroom",this.getLayout().split("@")[1]);
		map.put("bathroom",this.getLayout().split("@")[2]);
		map.put("balcony",this.getLayout().split("@")[3]);
		map.put("description",this.getDescription());
//		String[] picsStr = pics.split("@");
//		map.put("layoutPic", picsStr[0]);
//		if(picsStr.length == 2){
//			map.put("pics",picsStr[1]);
//		}else if(picsStr.length > 2){
//			map.put("pics", pics.substring(picsStr[0].length()+1, picsStr.length()).split("@"));
//		}
		
		List<String> featuresList = new ArrayList<String>();
		Set<Feature> features = this.getFeatures();
		for(Feature f:features){
			featuresList.add(f.getDescription());
		}
		map.put("features",featuresList);
		return map;
	}

}
