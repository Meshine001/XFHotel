package com.xfhotel.hotel.service;

import java.util.List;
import java.util.Map;

import com.xfhotel.hotel.entity.Room;

public interface RoomService {
	public int add(Room room);
	
	public void update(Room room);
	
	public void delete(Room room);

	public Map getRoomInfo(Long id);
	
	public List<Room> getHomeRooms();

	public Room findById(Long id);

	public List<Map>  getAllRooms();
	
	public void updateRoomPic(Long id,String[] pics);
}
