package com.xfhotel.hotel.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xfhotel.hotel.dao.RoomDAO;
import com.xfhotel.hotel.entity.Room;

@Repository
public class RoomDAOImpl implements RoomDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int add(Room room) {
		// TODO Auto-generated method stub
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.saveOrUpdate(room);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

}
