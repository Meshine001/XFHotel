package com.xfhotel.hotel.dao;

import java.util.List;

import com.xfhotel.hotel.entity.LeaseType;

public interface LeaseTypeDAO {
	public List<LeaseType> listLeaseTypes();

	public List findApartmentTypeLeases(long id);
}
