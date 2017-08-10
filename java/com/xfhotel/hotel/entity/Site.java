package com.xfhotel.hotel.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_site")
public class Site {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String place;
	private Double price;
	private int classify;
	
	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getClassify() {
		return classify;
	}
	public void setClassify(int classify) {
		this.classify = classify;
	}
	
	public Map toMap(){
		Map info = new HashMap();
		info.put("id", id);
		info.put("place", place);
		info.put("price", price);
		info.put("classify", classify);
		return info;
	}
}
