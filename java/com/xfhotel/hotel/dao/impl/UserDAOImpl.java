package com.xfhotel.hotel.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xfhotel.hotel.dao.UserDAO;
import com.xfhotel.hotel.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(u);
		logger.info("娣诲姞鐢ㄦ埛鎴愬姛锛�" + u);
	}

	@Override
	public void updateUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(u);
		logger.info("鏇存柊鐢ㄦ埛鎴愬姛锛�" + u);
	}

	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> list = session.createQuery("from User").list();
		logger.info("鏌ヨ鎵�鏈夌敤鎴�");
		for (User u : list) {
			logger.info("" + u);
		}
		return list;
	}

	@Override
	public User getUserById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, new Long(id));
		logger.info("閫氳繃Id鏌ヨ:" + u);
		return u;
	}

	@Override
	public void removeUser(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, new Long(id));
		if (null != u) {
			session.delete(u);
		}
		logger.info("鍒犻櫎鐢ㄦ埛:" + u);
	}

	@Override
	public User getUser(String username, String password) {
		String hql = "from User where username = ? and password = ?";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, username);
		query.setString(1, password);
		List<User> list = query.list();
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

}
