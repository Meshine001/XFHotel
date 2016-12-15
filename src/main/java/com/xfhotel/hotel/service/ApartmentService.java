package com.xfhotel.hotel.service;

import java.util.HashMap;

import com.xfhotel.hotel.entity.Apartment;

public interface ApartmentService {
	public String add(Apartment apartment);
	public Apartment findById(long id);
	public HashMap getApartmentInfo(long id);
}
