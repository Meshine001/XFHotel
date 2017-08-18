package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xfhotel.hotel.dao.impl.UserDAOImpl;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAOImpl userDAO;
	
	@Transactional
	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userDAO.get(id);
	}
	
	@Transactional
	@Override
	public void add(User t) {
		// TODO Auto-generated method stub
		userDAO.save(t);
	}

	@Transactional
	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		userDAO.delete(t);
	}

	
	@Transactional
	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		userDAO.update(t);
	}
	
	@Transactional
	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return userDAO.getListByHQL("from Customer order by id desc", null);
	}

	@Transactional
	@Override
	public User login(String username, String password) {
		String hql = "from User where username = ? and password = ?";
		String[] values = { username, password };
		return  userDAO.getByHQL(hql, values);
	}



}
