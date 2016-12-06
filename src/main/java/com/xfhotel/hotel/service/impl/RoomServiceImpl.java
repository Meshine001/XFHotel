package com.xfhotel.hotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.RoomDAO;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDAO roomDAO;
	
	@Override
	@Transactional
	public int add(Room room) {
		// TODO Auto-generated method stub
		return roomDAO.add(room);
	}

}
