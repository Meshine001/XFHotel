package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.FacilityDAO;
import com.xfhotel.hotel.dao.impl.FacilityDAOImpl;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.service.FacilityService;

@Service
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	FacilityDAOImpl facilityDAO;
	
	@Override
	@Transactional
	public List<Facility> listFacilities() {
		// TODO Auto-generated method stub
		return facilityDAO.listFacilities();
	}

	@Override
	@Transactional
	public Facility findById(Long id) {
		// TODO Auto-generated method stub
		return facilityDAO.get(id);
	}
	@Transactional
	@Override
	public void add(Facility f) {
		facilityDAO.saveOrUpdate(f);
	}
	@Transactional
	@Override
	public void delete(Facility f) {
		facilityDAO.delete(f);
	}

}
