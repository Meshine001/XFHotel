package com.xfhotel.hotel.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, ID extends Serializable>{
	public abstract T findById(ID id);
	public abstract void add(T t);
	public abstract void delete(T t);
	public abstract void update(T t);
	public abstract List<T> list();
	
}
