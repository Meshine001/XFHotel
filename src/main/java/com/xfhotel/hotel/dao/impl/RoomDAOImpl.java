package com.xfhotel.hotel.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xfhotel.hotel.dao.RoomDAO;
import com.xfhotel.hotel.entity.Apartment;
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

	@Override
	public Room getRoomById(Long id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Room.class);
		c.add(Restrictions.eq("id", id));
		c.setFetchMode("facilities", FetchMode.JOIN).setFetchMode("prices", FetchMode.JOIN);
		return (Room) c.uniqueResult();
	}

	@Override
	public Long getApartmentId(Long id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Room.class);
		c.add(Restrictions.eq("id", id));
		Room room = (Room) c.setFetchMode("apartment", FetchMode.JOIN).uniqueResult();
		return room.getApartment().getId();
	}

}
