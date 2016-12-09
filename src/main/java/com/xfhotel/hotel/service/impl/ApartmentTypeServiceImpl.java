package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.ApartmentTypeDAO;
import com.xfhotel.hotel.entity.ApartmentType;
import com.xfhotel.hotel.service.ApartmentTypeService;

@Service
public class ApartmentTypeServiceImpl implements ApartmentTypeService {

	@Autowired
	private ApartmentTypeDAO apartmentTypeDAO;
	
	@Override
	@Transactional
	public List<ApartmentType> listApartmentTypes() {
		// TODO Auto-generated method stub
		return apartmentTypeDAO.listApartmentTypes();
	}

	@Override
	@Transactional
	public ApartmentType findById(Long id) {
		// TODO Auto-generated method stub
		return apartmentTypeDAO.findById(id);
	}

}
