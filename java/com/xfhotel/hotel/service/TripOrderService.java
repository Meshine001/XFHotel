package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.TripOrder;

public interface TripOrderService extends  BaseService<TripOrder, Long>{
	public TripOrder getTripOrder(Long oederId);
	public TripOrder getByPayNo(String payNo);
	public List<TripOrder> list();

}
