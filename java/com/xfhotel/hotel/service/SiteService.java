package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Site;

public interface SiteService extends BaseService<Site, Long>{
	public List<Site> list();
	public List<Site> list1(int classify);
}
