package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Facility;

public interface FacilityService {
	public List<Facility> listFacilities();
	public Facility findById(Long id);
}
