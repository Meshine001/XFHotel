package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.support.PageResults;

public interface CustomerService {
	public Customer login(String tel,String password);
	public boolean register(Customer c,CustomerDetails details);
	public Customer modify(CustomerDetails c,int cId);
	public boolean checkTel(String tel);
	public void logout();
	
	public String changePsd(String oldPsd,String psd,int id);
	public PageResults<Customer> list(int page);
}
