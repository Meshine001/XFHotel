 package com.xfhotel.hotel.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.xfhotel.hotel.common.Constants;

@Entity
@Table(name = "t_apartment")
public class Apartment {
	
	public final static int TYPE_NUM = 4;
	public final static int TYPE_ALL = 0;
	public final static int TYPE_HOTEL = 1;
	public final static int TYPE_APARTMENT = 2;
	public final static int TYPE_PLAY_ROOM = 3;
	
	public final static String getTypeDescription(int type){
		switch(type){
		case 0:
			return "不限";
		case 1:
			return "酒店型";
		case 2:
			return "公寓型";
		case 3:
			return "休闲型";
		default:
			return "不限";
		}
	}

	// Position
	// facilities
	// map
	@Id
	@GeneratedValue(generator = "apartmentgenerator")
	@GenericGenerator(name = "apartmentgenerator", strategy = "increment")
	private long id;
	// coordinate 经纬度
	private double latitude; // for map
	private double longitude;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "apartment_id")
	public List<Room> rooms; //

	public String facilities; //
	public String features;// label for apartment

	public String prices;// day@weekend@month@year
	public String price_scope;

	private String apartmentType; // hotel or apartment

	private String type; // together,sigle

	private String address;// address : cell : num_building

	private String floor;// current floor : total floor

	private String direction;//
	private double square;// square for apartment
	private String capacity;// num of people
	private String layout;// bedroom : livingroom : bathroom : balcony

	private String description;// enviroment,transportation,etc.
	private String lockAddress;
	private String pic1;// layoutPic
	private String pic2;// ke ting, can ting
	private String pic3;// bathroom chufang
	private String pic4;// xiao qu

	private Long createTime;

	public Apartment() {
		// TODO Auto-generated constructor stub
	}
	
	

	public String getLockAddress() {
		return lockAddress;
	}



	public void setLockAddress(String lockAddress) {
		this.lockAddress = lockAddress;
	}



	public String getPrice_scope() {
		return price_scope;
	}

	public void setPrice_scope(String price_scope) {
		this.price_scope = price_scope;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
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

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public String[] getFacilities() {
		return facilities.substring(1).split("@");
	}

	public void setFacilities(String[] facilities) {
		if (facilities == null) {
			this.facilities = "";
			return;
		}
		String str = "";
		for (int i = 0; i < facilities.length; i++) {
			str = str + "@";
			str = str + facilities[i];
		}
		this.facilities = str;
	}

	public String[] getFeatures() {
		return features.split("@");
	}

	public void setFeatures(String features[]) {
		if (features == null) {
			this.features = "";
			return;
		}
		String str = "";
		for (int i = 0; i < features.length; i++) {
			if (i > 0)
				str = str + "@";
			str = str + features[i];
		}
		this.features = str;
	}

	public String getPrices() {
		return prices;
	}

	public void setPrices(String prices) {
		this.prices = prices;
		Double tmp = Double.valueOf(prices.split("@")[0]);
		for (int i = 0; i < Constants.price_scope.length; i++) {
			if (tmp < Constants.price_scope[i]) {
				this.price_scope = i + "";
			}
		}
		this.price_scope = Constants.price_scope.length + "";
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

	public Map toMap() {
		Map map = new HashMap();
		map.put("id", this.getId());
		map.put("latitude", this.getLatitude());
		map.put("longitude", this.getLongitude());
		map.put("apartmenttype", apartmentType);
		map.put("type", this.getType());
		map.put("address", this.getAddress().split("@")[0]);
		map.put("community", this.getAddress().split("@")[1]);
		map.put("num_building", this.getAddress().split("@")[2]);
		map.put("location", this.getAddress().split("@")[3]);
		map.put("floor", this.getFloor().split("@")[0]);
		map.put("totalfloor", this.getFloor().split("@")[1]);
		map.put("num_unit", floor.split("@")[2]);
		map.put("num_door", floor.split("@")[3]);
		map.put("direction", this.getDirection());
		map.put("square", this.getSquare());
		map.put("capacity", this.getCapacity());
		map.put("lock_address", this.lockAddress);
		map.put("bedroom", this.getLayout().split("@")[0]);
		map.put("livingroom", this.getLayout().split("@")[1]);
		map.put("bathroom", this.getLayout().split("@")[2]);
		map.put("balcony", this.getLayout().split("@")[3]);
		map.put("descriptionAround", this.getDescription());
		map.put("pic1", pic1);
		map.put("pic2", pic2.split("@"));
		map.put("pic3", pic3.split("@"));
		map.put("pic4", pic4.split("@"));
		List<String> picShow = new ArrayList<String>();
		for(String p:pic2.split("@")){
			picShow.add(p);
		}
		for(String p:pic3.split("@")){
			picShow.add(p);
		}
		map.put("picShow", picShow);
		map.put("dayPrice", prices);

		ArrayList rooms = new ArrayList();
		Iterator itr = this.getRooms().iterator();
		while (itr.hasNext()) {
			Room r = (Room) itr.next();
			rooms.add(r.toMap());
		}
		map.put("rooms", rooms);
		map.put("facilities", this.getFacilities());
		map.put("features", this.getFeatures());
		map.put("prices", prices.split("@"));
		return map;
	}
	
	public static int getTypeNum(String s){
		if(s.equals("酒店型")){
			return Apartment.TYPE_HOTEL;
		}else{
			return Apartment.TYPE_PLAY_ROOM;
		}
		
	}

}
