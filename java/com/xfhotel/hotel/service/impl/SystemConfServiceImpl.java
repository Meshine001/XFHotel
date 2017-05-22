package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.SystemConfDAOImpl;
import com.xfhotel.hotel.entity.SystemConfig;
import com.xfhotel.hotel.service.SystemConfService;

@Service
public class SystemConfServiceImpl implements SystemConfService{

	@Autowired
	SystemConfDAOImpl systemConfDAO;
	
	@Override
	public SystemConfig findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(SystemConfig t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SystemConfig t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SystemConfig t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SystemConfig> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public SystemConfig getConfig() {
		// TODO Auto-generated method stub
		return systemConfDAO.get(1L);
	}

}
