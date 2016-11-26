package com.xfhotel.hotel.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		logger.info("添加用户成功："+u);
	}

	@Override
	public void updateUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(u);
		logger.info("更新用户成功："+u);
	}

	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> list = session.createQuery("from User").list();
		logger.info("查询所有用户");
		for(User u:list){
			logger.info(""+u);
		}
		return list;
	}

	@Override
	public User getUserById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, new Long(id));
		logger.info("通过Id查询:"+u);
		return u;
	}

	@Override
	public void removeUser(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, new Long(id));
		if(null != u){
			session.delete(u);
		}
		logger.info("删除用户:"+u);
	}

}
