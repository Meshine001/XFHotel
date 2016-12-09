package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.ApartmentType;

public interface ApartmentTypeService {
	public List<ApartmentType> listApartmentTypes();
	public ApartmentType findById(Long id);
}
