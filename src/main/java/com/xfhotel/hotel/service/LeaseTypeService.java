package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.LeaseType;

public interface LeaseTypeService {
	public List<LeaseType> listLeaseTypes();
	public LeaseType findById(Long id);
	public List findApartmentTypeLeases(long id);

}
