package com.xfhotel.hotel.dao;

import java.util.List;

import com.xfhotel.hotel.entity.Apartment;

public interface ApartmentDAO {
	public int addApartment(Apartment apartment);
	public int updateApartment(Apartment apartment);
	public List<Apartment> listApartments();
	public Apartment getApartmentById(long id);
	public int removeApartment(long id);
}
