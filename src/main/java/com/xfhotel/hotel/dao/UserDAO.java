package com.xfhotel.hotel.dao;

import java.util.List;

import com.xfhotel.hotel.entity.User;

public interface UserDAO {
	public void addUser(User u);
	public void updateUser(User u);
	public List<User> listUsers();
	public User getUserById(long id);
	public void removeUser(long id);
	public User getUser(String username,String password);
}
