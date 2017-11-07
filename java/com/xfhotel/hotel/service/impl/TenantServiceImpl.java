package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.dao.impl.TenantDAOImpl;
import com.xfhotel.hotel.entity.Fitness;
import com.xfhotel.hotel.entity.Tenant;
import com.xfhotel.hotel.service.TenantService;
import com.xfhotel.hotel.support.Message;


@Service
public class TenantServiceImpl implements TenantService {

	@Autowired
	TenantDAOImpl tenantDAO;
	
	@Transactional
	@Override
	public Tenant findById(Long id) {
		// TODO Auto-generated method stub
		return tenantDAO.get(id);
	}

	@Transactional
	@Override
	public void add(Tenant t) {
		// TODO Auto-generated method stub
		tenantDAO.save(t);
	}

	@Transactional
	@Override
	public void delete(Tenant t) {
		// TODO Auto-generated method stub
		tenantDAO.delete(t);
	}

	@Transactional
	@Override
	public void update(Tenant t) {
		// TODO Auto-generated method stub
		tenantDAO.update(t);
	}

	@Transactional
	@Override
	public List<Tenant> list() {
		// TODO Auto-generated method stub
		return tenantDAO.getListByHQL("from Tenant order by id desc", null);
	}
	
	@Transactional
	@Override
	public Tenant getTenant(String userName , String password) {
		String hql = "from Tenant where userName=? and password = ?";
		Object[] v = {userName , password};
		return tenantDAO.getByHQL(hql, v);
	}



}