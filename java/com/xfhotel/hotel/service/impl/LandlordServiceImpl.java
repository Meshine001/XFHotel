package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.LandlordDAOImpl;
import com.xfhotel.hotel.entity.Landlord;
import com.xfhotel.hotel.service.LandlordService;

@Service
public class LandlordServiceImpl implements LandlordService{

	@Autowired
	LandlordDAOImpl landlordDao;
	
	@Transactional
	@Override
	public Landlord findById(Long id) {
		// TODO Auto-generated method stub
		return landlordDao.get(id);
	}
	
	@Transactional
	@Override
	public void add(Landlord t) {
		// TODO Auto-generated method stub
		landlordDao.save(t);
	}

	@Transactional
	@Override
	public void delete(Landlord t) {
		// TODO Auto-generated method stub
		landlordDao.delete(t);
	}

	@Transactional
	@Override
	public void update(Landlord t) {
		// TODO Auto-generated method stub
		landlordDao.update(t);
	}

	@Transactional
	@Override
	public List<Landlord> list() {
		// TODO Auto-generated method stub
		return landlordDao.getListByHQL("from Landlord order by id desc", null);
	}

	@Transactional
	@Override
	public Landlord getCustomer(Long uId) {
		// TODO Auto-generated method stub
		String hql = "from Landlord  where uId=?";
		Object[] v = {uId};
		return landlordDao.getByHQL(hql, v);
		
	}

	@Override
	public List<Landlord> getLandlord() {
		// TODO Auto-generated method stub
		return null;
	}

}
