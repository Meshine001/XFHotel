package com.xfhotel.hotel.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import com.xfhotel.hotel.support.TimeUtil;

@Entity
@Table(name = "t_price")
public class Price {
	@Id
	@GeneratedValue(generator="facilitygenerator")
	@GenericGenerator(name="facilitygenerator",strategy="increment")
	private long id;
	private long apartment_id;
	private long date;
	private Double price;
//	private int status;
	public Price() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Price(Long apartment_id, long date, Double price ) {
		super();
		this.apartment_id = apartment_id;
		this.date = date;
		this.price = price;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDate() {
		return TimeUtil.getDateStr(date);
	}
	
	public void setDate(long date) {
		this.date = date;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	public long getApartment_id() {
		return apartment_id;
	}

	public void setApartment_id(long apartment_id) {
		this.apartment_id = apartment_id;
	}

//	
//	public int getStatus() {
//		return status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}

	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", TimeUtil.getDateStr(date));
		map.put("price", price);
//		map.put("status", status);
		return map;
	}
	
	
}
