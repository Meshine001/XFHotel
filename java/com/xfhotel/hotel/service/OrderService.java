package com.xfhotel.hotel.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.PageResults;

public interface OrderService {
	
	public void add(Order o);
	public void update(Order o);
	public void delete(Order o);
	public Order get(Long id);
	public Order postOrder(Long cusId, String description, Long roomId, String cusName, String cusTel,
			String otherCusName[], String otherCusIdCard[], String cusIdCard, String personal, String startTime,
			String endTime, Integer totalDay, String price, String totalPrice, String preferential, boolean needFapiao,
			String apartmentType,Long counponId);
	public Order getByPayNo(String payNo);
	public List<Order> list(int type);
	public PageResults<Order> listPage(int page);
	public List<Order> listDiedOrders(int type);
	public List<Order> getCustomerOrders(Long cId,int type);
	public List<Order> checkAvailable(Long id,String startTime,String endTime);
	public boolean isTimeOut(Order o);
	public void refreshAll();
	public void refresh(Long cId);
	public List<Order> search(Long cId, int category, int type, String startDate, String endDate, int range);
	public Message outLease(Long id);
	public List<Order> getOrders(Long roomId);
}
