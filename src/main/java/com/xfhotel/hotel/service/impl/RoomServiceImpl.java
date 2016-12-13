package com.xfhotel.hotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.dao.RoomDAO;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.entity.Price;
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

	@Override
	public HashMap getRoomInfo(Long id) {
		// TODO Auto-generated method stub
		Room room = roomDAO.getRoomById(id);
		HashMap map = room.toMap();
		
		ArrayList facilities = new ArrayList();
		Iterator itfc = room.getFacilities().iterator();
		while(itfc.hasNext()){
			Facility f = (Facility) itfc.next();
			facilities.add(f.toMap());
		}
		map.put("facilities", facilities);
		
		ArrayList prices = new ArrayList();
		Iterator itp = room.getPrices().iterator();
		while(itp.hasNext()){
			Price p = (Price) itp.next();
			prices.add(p.toMap());
		}
		map.put("prices", prices);
		return map;
	}

}
