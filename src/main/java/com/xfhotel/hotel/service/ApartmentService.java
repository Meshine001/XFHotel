package com.xfhotel.hotel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Price;
import com.xfhotel.hotel.support.PageResults;

public interface ApartmentService {
	public String add(Apartment apartment);
	public void update(Apartment apartment);
	public void delete(Apartment apartment);
	public Apartment findById(long id);
	public Map getApartmentInfo(long id);
	public List findApartment(String content, Apartment apartment);
	public List list();
	public PageResults<Map> getPage(int page); 
	public Price getSpPrice(Apartment apartment,Long date);
	public void setSpPrice(Price price);
	public List<Map> getSpPrices(Long start,Long end,Apartment apartment);
	public Map<String, Object> caculatePrice(String startTime, String endTime, Long apartmentId);
}
