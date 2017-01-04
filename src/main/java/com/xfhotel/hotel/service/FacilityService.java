package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Facility;

public interface FacilityService {
	public List<Facility> listFacilities();
	public Facility findById(Long id);
	public void add(Facility f);
	public void delete(Facility f);
}
