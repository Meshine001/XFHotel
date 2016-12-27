package com.xfhotel.hotel.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_feature")
public class Feature implements Serializable {
	
	@Id
	@GeneratedValue(generator="featuregenerator")
	@GenericGenerator(name="featuregenerator",strategy="increment")
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
