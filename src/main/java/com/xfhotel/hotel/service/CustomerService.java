package com.xfhotel.hotel.service;

import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;

public interface CustomerService {
	public Customer login(String tel,String password);
	public boolean register(Customer c,CustomerDetails details);
	public Customer modify(CustomerDetails c,int cId);
}