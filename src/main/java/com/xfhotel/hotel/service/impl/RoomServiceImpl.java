package com.xfhotel.hotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

import com.xfhotel.hotel.dao.FacilityDAO;
import com.xfhotel.hotel.dao.FeatureDAO;
import com.xfhotel.hotel.dao.RoomDAO;
import com.xfhotel.hotel.dao.impl.ApartmentDAOImpl;
import com.xfhotel.hotel.dao.impl.FacilityDAOImpl;
import com.xfhotel.hotel.dao.impl.RoomDAOImpl;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Facility;
import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.entity.Room;
import com.xfhotel.hotel.service.RoomService;
import com.xfhotel.hotel.support.StringSplitUtil;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDAOImpl roomDAO;
	@Autowired
	private ApartmentDAOImpl apartmentDAO;

	@Autowired
	private FacilityDAOImpl facilityDAO;;

	@Override
	@Transactional
	public int add(Room room) {
		// TODO Auto-generated method stub
		return roomDAO.add(room);
	}

	@Override
	@Transactional
	public Map getRoomInfo(Long id) {
		// TODO Auto-generated method stub
		Room room = roomDAO.getRoomById(id);
		Map map = room.toMap();
		List<Map> facilities = new ArrayList<Map>();
		String[] ids = (String[]) map.get("facilities");
		for (String i : ids) {
			if(i.equals(""))continue;
			Map f = null;
			Facility fa = facilityDAO.get(Long.valueOf(i));
			if (fa == null)
				continue;
			f = fa.toMap();
			facilities.add(f);
		}
		map.put("facilityEntity", facilities);
		return map;
	}

	@Override
	@Transactional
	public Room findById(Long id) {
		// TODO Auto-generated method stub
		return roomDAO.getRoomById(id);
	}

	@Transactional
	@Override
	public List<Map> getAllRooms() {
		
		Integer[] value = {Room.STATUS_IDLE,Room.STATUS_WILL_IDLE};
		List<Room> rooms = roomDAO.getListByHQL("from Room where status =? or status =?",value);
		;
		List<Map> list = new ArrayList<Map>();
		for (Room r : rooms) {
			list.add(this.getRoomInfo(r.getId()));
		}

		return list;
	}

	@Transactional
	@Override
	public void update(Room room) {
		roomDAO.update(room);
	}

	@Transactional
	@Override
	public void updateRoomPic(Long id, String[] pics) {
		Room room = roomDAO.get(id);
		String pic = StringSplitUtil.buildStrGroup(pics);
		room.setPics(pic);
		roomDAO.update(room);
	}

	@Transactional
	@Override
	public void delete(Room room) {
		roomDAO.delete(room);
	}

}
