package com.xfhotel.hotel.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_facility")
public class Facility implements Serializable{
	@Id
	private long id;
	private String description;
	
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
	public HashMap toMap() {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("description", description);
		map.put("id", id);
		return map;
	}
	
	

}
