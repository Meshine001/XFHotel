package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.User;

public interface UserService {
	public void addUser(User u);

	public void updateUser(User u);

	public List<User> listUsers();

	public User getUserById(long id);

	public void removeUser(long id);
	
	public User getUser(String username,String password);
}
