package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.HouseDAOImpl;
import com.xfhotel.hotel.entity.House;
import com.xfhotel.hotel.service.HouseService;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	HouseDAOImpl houseDAO;
	
	@Override
	public House findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public void add(House house) {
		// TODO Auto-generated method stub
		houseDAO.save(house);
	}
	@Override
	public void delete(House t) {
		// TODO Auto-generated method stub
	}
	@Transactional
	@Override
	public void update(House house) {
		// TODO Auto-generated method stub
		houseDAO.update(house);
	}
	@Transactional
	@Override
	public List<House> list() {
		// TODO Auto-generated method stub
		return houseDAO.getListByHQL("from House order by id desc", null);
	}

	@Transactional
	@Override
	public House getHouse(Long apartmentId, Long data) {
		String hql = "from House where apartmentId = ? and date = ?";
		Object[] values = { apartmentId, data};
		return houseDAO.getByHQL(hql, values);
	}
	@Transactional
	@Override
	public List<House> getHouseId(Long apartmentId) {
		String hql = "from House where apartmentId=?";
		Object[] v = {apartmentId};
		return houseDAO.getListByHQL(hql, v);
		}

}
