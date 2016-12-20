package com.xfhotel.hotel.service;

import java.util.HashMap;
import java.util.List;

import com.xfhotel.hotel.entity.ApartmentType;
import com.xfhotel.hotel.entity.Room;

public interface RoomService {
	public int add(Room room);

	public HashMap getRoomInfo(Long id);

	public Room findById(Long id);

	public Long getApartmentType(Long id);
	
	public List<Room> getAllRooms();
}
