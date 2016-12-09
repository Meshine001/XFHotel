package com.xfhotel.hotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.CustomerDAOImpl;
import com.xfhotel.hotel.dao.impl.CustomerDetailsDAOImpl;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerDAOImpl customerDAO;
	@Autowired
	CustomerDetailsDAOImpl customerDetailsDAO;
	
	@Transactional
	@Override
	public Customer login(String tel, String password) {
		String hql = "from Customer where tel = ? and password = ?";
		String[] values = {tel,password};
		return customerDAO.getByHQL(hql, values);
	}

	@Transactional
	@Override
	public boolean register(Customer c,CustomerDetails details) {
		try {
			customerDetailsDAO.saveOrUpdate(details);
			customerDAO.save(c);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}

	@Transactional
	@Override
	public Customer modify(CustomerDetails c,int cId) {
		// TODO Auto-generated method stub
		customerDetailsDAO.saveOrUpdate(c);
		
		return customerDAO.get(cId);
	}

}
