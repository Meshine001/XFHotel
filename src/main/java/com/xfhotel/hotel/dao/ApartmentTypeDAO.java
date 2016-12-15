package com.xfhotel.hotel.dao;

import java.util.List;

import com.xfhotel.hotel.entity.ApartmentType;

public interface ApartmentTypeDAO {
	public List<ApartmentType> listApartmentTypes();

	public ApartmentType findById(Long id);
}
