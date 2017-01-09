package com.xfhotel.hotel.service;

import java.util.List;

public interface BaseService<T> {
	public void add(T t);
	public void delete(T t);
	public void update(T t);
	public List<T> list();
	
}
