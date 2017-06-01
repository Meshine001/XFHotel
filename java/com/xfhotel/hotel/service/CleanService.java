package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Clean;
import com.xfhotel.hotel.entity.Order;


public interface CleanService extends BaseService<Clean, Long> {

	public Clean get(Long id);
	public List<Clean> list();
	public List<Clean> getClean(Long oederId);
}
