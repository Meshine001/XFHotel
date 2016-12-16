package com.xfhotel.hotel.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_apartmenttype")
public class ApartmentType {
	@Id
	private long id;
	private String description;
	
	@OneToMany
	@JoinColumn(name="apartmentType_id")
	public Set<Apartment> apartments;
	
	@ManyToMany(cascade={CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinTable(name="t_apartmenttype_leasetype",
		joinColumns={@JoinColumn(name="apartmenttype_id")},
		inverseJoinColumns={@JoinColumn(name="leasetype_id")})
	public Set<LeaseType> leaseTypes;

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

	public Set<Apartment> getApartments() {
		return apartments;
	}

	public void setApartments(Set<Apartment> apartments) {
		this.apartments = apartments;
	}

	public Set<LeaseType> getLeaseTypes() {
		return leaseTypes;
	}

	public void setLeaseTypes(Set<LeaseType> leaseTypes) {
		this.leaseTypes = leaseTypes;
	}
	
	
}
