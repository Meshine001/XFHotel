package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.SiteDAOImpl;
import com.xfhotel.hotel.entity.Site;
import com.xfhotel.hotel.service.SiteService;

@Service
public class SiteServiceImpl implements SiteService{
	
	@Autowired
	SiteDAOImpl siteDao;

	@Transactional
	@Override
	public Site findById(Long id) {
		// TODO Auto-generated method stub
		return siteDao.get(id);
	}

	@Transactional
	@Override
	public void add(Site t) {
		// TODO Auto-generated method stub
		siteDao.save(t);
	}

	@Transactional
	@Override
	public void delete(Site t) {
		// TODO Auto-generated method stub
		siteDao.delete(t);
	}

	@Transactional
	@Override
	public void update(Site t) {
		// TODO Auto-generated method stub
		siteDao.update(t);
	}

	@Transactional
	@Override
	public List<Site> list() {
		// TODO Auto-generated method stub
		return siteDao.getListByHQL("from Site order by id desc", null);
	}

	@Transactional
	@Override
	public List<Site> list1(int classify) {
		// TODO Auto-generated method stub
		String hql = "from Site where classify=?";
		Object[] v = {classify};
		return siteDao.getListByHQL(hql, v);
	}

}
