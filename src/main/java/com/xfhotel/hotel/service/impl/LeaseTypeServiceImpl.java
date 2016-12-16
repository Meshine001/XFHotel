package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.LeaseTypeDAOImpl;
import com.xfhotel.hotel.entity.LeaseType;
import com.xfhotel.hotel.service.LeaseTypeService;

@Service
public class LeaseTypeServiceImpl implements LeaseTypeService {

	@Autowired
	LeaseTypeDAOImpl leaseTypeDAO;
	
	@Override
	@Transactional
	public List<LeaseType> listLeaseTypes() {
		// TODO Auto-generated method stub
		return leaseTypeDAO.listLeaseTypes();
	}

	@Override
	@Transactional
	public List findApartmentTypeLeases(long id) {
		// TODO Auto-generated method stub
		return leaseTypeDAO.findApartmentTypeLeases(id);
	}

	@Override
	@Transactional
	public LeaseType findById(Long id) {
		// TODO Auto-generated method stub
		return leaseTypeDAO.get(id);
	}

}
