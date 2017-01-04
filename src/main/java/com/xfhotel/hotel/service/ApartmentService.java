package com.xfhotel.hotel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfhotel.hotel.entity.Apartment;

public interface ApartmentService {
	public String add(Apartment apartment);
	public void update(Apartment apartment);
	public void delete(Apartment apartment);
	public Apartment findById(long id);
	public Map getApartmentInfo(long id);
	public List findApartment(String content, Apartment apartment);
	public List list();
}
