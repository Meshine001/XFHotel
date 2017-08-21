package com.xfhotel.hotel.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.FacilityDAOImpl;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.service.FacilityService;
@Service
public class FacilityServiceImpl implements FacilityService{
	@Autowired
	FacilityDAOImpl facilityDao;
	
	@Transactional
	@Override
	public Facility findById(Long id) {
		// TODO Auto-generated method stub
		return facilityDao.get(id);
	}
	
	@Transactional
	@Override
	public void add(Facility t) {
		// TODO Auto-generated method stub
		facilityDao.saveOrUpdate(t);
	}

	@Transactional
	@Override
	public void delete(Facility t) {
		// TODO Auto-generated method stub
		facilityDao.delete(t);
	}

	@Transactional
	@Override
	public void update(Facility t) {
		// TODO Auto-generated method stub
		facilityDao.update(t);
	}

	@Transactional
	@Override
	public List<Facility> list() {
		return facilityDao.getListByHQL("from Facility", null);
	}
	@Transactional
	@Override
	public List<Facility> list1(int classify) {
		// TODO Auto-generated method stub
				String hql = "from Facility where classify=?";
				Object[] v = {classify};
				return facilityDao.getListByHQL(hql, v);
	}

	@Transactional
	@Override
	public List<Facility> getFacility(long oederId) {
		// TODO Auto-generated method stub
		String hql = "from Facility where oederId=?";
		Object[] v = {oederId};
		return facilityDao.getListByHQL(hql, v);
	}
	


}
