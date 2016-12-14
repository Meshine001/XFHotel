package com.xfhotel.hotel.dao;

import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Room;

public interface RoomDAO {
	public int add(Room room);

	public Room getRoomById(Long id);

	public Long getApartmentId(Long id);
}
