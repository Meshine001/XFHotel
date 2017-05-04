package com.xfhotel.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.UserDAO;
import com.xfhotel.hotel.entity.User;
import com.xfhotel.hotel.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public void addUser(User u) {
		this.userDAO.addUser(u);
	}

	@Override
	@Transactional
	public void updateUser(User u) {
		this.userDAO.updateUser(u);
	}

	@Override
	@Transactional
	public List<User> listUsers() {
		return this.userDAO.listUsers();
	}

	@Override
	@Transactional
	public User getUserById(long id) {
		return this.userDAO.getUserById(id);
	}

	@Override
	@Transactional
	public void removeUser(long id) {
		this.userDAO.removeUser(id);
	}

	@Override
	@Transactional
	public User getUser(String username, String password) {
		return this.userDAO.getUser(username, password);
	}

}
