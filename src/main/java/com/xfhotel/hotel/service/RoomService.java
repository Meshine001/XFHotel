package com.xfhotel.hotel.service;

import java.util.HashMap;

import com.xfhotel.hotel.entity.Room;

public interface RoomService {
	public int add(Room room);

	public HashMap getRoomInfo(Long id);
}
