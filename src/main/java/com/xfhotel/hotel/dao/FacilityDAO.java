package com.xfhotel.hotel.dao;

import java.util.List;

import com.xfhotel.hotel.entity.Facility;

public interface FacilityDAO {
	public List<Facility> listFacilities();
	public Facility findById(long id);
}
