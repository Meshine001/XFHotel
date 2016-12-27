package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Order;

public interface OrderService {
	
	public static final int LIST_TYPE_ALL = 0;
	public static final int LIST_TYPE_NOT_COMPLETED = 1;
	public static final int LIST_TYPE_COMPLETED = 2;
	public static final int LIST_TYPE_CANCELED = 3;
	
	public void add(Order o);
	public void update(Order o);
	public void delete(Order o);
	public Order get(Long id);
	public List<Order> list(int type);
}
