package com.xfhotel.hotel.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.impl.CustomerDAOImpl;
import com.xfhotel.hotel.dao.impl.CustomerDetailsDAOImpl;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.CustomerDetails;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.support.PageResults;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDAOImpl customerDAO;
	@Autowired
	CustomerDetailsDAOImpl customerDetailsDAO;
	
	@Autowired
	HttpSession session;
	
	@Transactional
	@Override
	public Customer login(String tel, String password) {
		String hql = "from Customer where tel = ? and password = ?";
		String[] values = { tel, password };
		return customerDAO.getByHQL(hql, values);
	}

	@Transactional
	@Override
	public boolean register(Customer c, CustomerDetails details) {
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
	public Customer modify(CustomerDetails c, long Id) {
		// TODO Auto-generated method stub
		customerDetailsDAO.saveOrUpdate(c);

		return customerDAO.get(Id);
	}

	@Transactional
	@Override
	public boolean checkTel(String tel) {
		String hql = "from Customer where tel = ?";
		String[] values = { tel };
		Customer c = customerDAO.getByHQL(hql, values);
		return c != null ? true : false;
	}
	
	@Transactional
	@Override
	public String changePsd(String oldPsd, String psd,long id) {
		Customer c = customerDAO.get(id);
		if(c.getPassword().equals(oldPsd)){
			c.setPassword(psd);
			customerDAO.update(c);
			return "修改成功";
		}else{
			return "原密码错误";
		}
	}

	@Override
	public void logout() {
		session.removeAttribute("c");
	}

	@Override
	@Transactional
	public PageResults<Customer> list(int page) {
		// TODO Auto-generated method stub
		return customerDAO.findPageByFetchedHql("from Customer order by id desc", "select count(*) from Customer", page, 10, null);
	}
	
	@Override
	@Transactional
	public void changeStatus(long id, int status) {
		// TODO Auto-generated method stub
		Customer c = customerDAO.get(id);
		if(status==1){
			c.setStatus(0);
		}else{
			c.setStatus(1);
		}
		customerDAO.update(c);
	}

	@Override
	@Transactional
	public Customer getCustomer(long id) {
		// TODO Auto-generated method stub
		return customerDAO.get(id);
	}
	
	@Transactional
	@Override
	public List<Customer> list() {
		// TODO Auto-generated method stub
		return customerDAO.getListByHQL("from Customer order by id desc", null);
	}

	@Transactional
	@Override
	public Customer getFind(String tel) {
		// TODO Auto-generated method stub
		String hqlString = "from Customer where tel=? ";
		Object[] values = {tel};
		return customerDAO.getByHQL(hqlString, values);
	}
	
	@Transactional
	@Override
	public void updateBaseInfo(Customer c) {
		// TODO Auto-generated method stub
		customerDAO.saveOrUpdate(c);
	}
	@Transactional
	@Override
	public List<CustomerDetails> getlist() {
		// TODO Auto-generated method stub
		return customerDetailsDAO.getListByHQL("from CustomerDetails order by id desc", null);
	}

}
