package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.BannerDAOImpl;
import com.xfhotel.hotel.entity.Banner;

@Service
public class BannerService implements com.xfhotel.hotel.service.BannerService {

	@Autowired
	BannerDAOImpl bannerDAO;
	
	@Transactional
	@Override
	public void add(Banner t) {
		bannerDAO.save(t);
	}

	@Transactional
	@Override
	public void delete(Banner t) {
		bannerDAO.delete(t);
	}

	@Transactional
	@Override
	public void update(Banner t) {
		bannerDAO.update(t);
	}

	@Transactional
	@Override
	public List<Banner> list() {
		
		return null;
	}
	
	@Transactional
	@Override
	public List<Banner> getHomeBanner() {
		String hql = "from Banner where showHome=?";
		Object[] values = {true};
		return bannerDAO.getListByHQL(hql, values);
	}

}
