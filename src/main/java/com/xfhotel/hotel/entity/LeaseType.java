package com.xfhotel.hotel.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_leasetype")
public class LeaseType {
	@Id
	private long id;
	private String description;
	
	@OneToMany
	@JoinColumn(name="leasetype_id")
	private Set<Price> prices;
	
	@ManyToOne
	private ApartmentType apartmentType;
	
	
	public LeaseType() {
		super();
	}

	public ApartmentType getApartmentType() {
		return apartmentType;
	}

	public void setApartmentType(ApartmentType apartmentType) {
		this.apartmentType = apartmentType;
	}

	public LeaseType(String description, Set<Price> prices) {
		super();
		this.description = description;
		this.prices = prices;
	}
	
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
	public Set<Price> getPrices() {
		return prices;
	}
	public void setPrices(Set<Price> prices) {
		this.prices = prices;
	}
	
	
}
