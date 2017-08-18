package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.User;

public interface UserService extends BaseService<User, Long> {
	public User login(String username,String password);
	public List<User>  gtelogin(String username);
}
