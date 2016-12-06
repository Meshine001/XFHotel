package com.xfhotel.hotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.ApartmentDAO;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.service.ApartmentService;

@Service
public class ApartmentServiceImpl implements ApartmentService {
	@Autowired
	ApartmentDAO apartmentDAO;

	@Override
	@Transactional
	public String Add(Apartment apartment) {
		// TODO Auto-generated method stub
		//validation
		if( apartmentDAO.addApartment(apartment) == 1 )
			return "";
		return "";
	}

	@Override
	@Transactional
	public Apartment findById(long id) {
		// TODO Auto-generated method stub
		return apartmentDAO.getApartmentById(id);
	}

}
