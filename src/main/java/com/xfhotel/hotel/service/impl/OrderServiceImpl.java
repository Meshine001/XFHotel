package com.xfhotel.hotel.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.dao.impl.OrderDAOImpl;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.support.DateUtil;
import com.xfhotel.hotel.support.TimeUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAOImpl orderDAO;

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public void add(Order o) {
		//构造订单号
		Long start = DateUtil.getStartTime();
		Long end = DateUtil.getEndTime();
		String hql = "from Order where time>? and time<?";
		Object[] values = {start,end};
		List<Order> list = orderDAO.getListByHQL(hql, values);
		String payNo = ""+(list.size()+1);
		int len = payNo.length();
		StringBuffer sb = new StringBuffer();
		for(int i=len;i<5;i++){
			sb.append("0");
		}
		sb.append(payNo);
		
		o.setPayNo(DateUtil.format(new Date(), "yyyyMMdd")+sb.toString());
		orderDAO.saveOrUpdate(o);
	}

	@Transactional
	@Override
	public void update(Order o) {
		orderDAO.update(o);
	}

	@Transactional
	@Override
	public void delete(Order o) {
		orderDAO.delete(o);
	}

	@Transactional
	@Override
	public Order get(Long id) {
		return orderDAO.get(id);
	}

	@Transactional
	@Override
	public List<Order> list(int type) {
		if (type == Apartment.TYPE_ALL) {
			return orderDAO.getListByHQL("from Order", null);
		}
		String hql = "from Order where type=?";
		Integer[] values = new Integer[1];
		switch (type) {
		case Apartment.TYPE_APARTMENT:
			values[0] = Apartment.TYPE_APARTMENT;
		case Apartment.TYPE_PLAY_ROOM:
			values[0] = Apartment.TYPE_PLAY_ROOM;
		default:
			values[0] = Apartment.TYPE_HOTEL;
		}

		return orderDAO.getListByHQL(hql, values);
	}

	@Transactional
	@Override
	public List<Order> listDiedOrders(int type) {
		Long diedLine = new Date().getTime() - Constants.EFFECTIVE_ORDER_TIME_DURING;
		String hql = "from Order where type =? and time<" + diedLine;
		Integer[] values = new Integer[1];
		switch (type) {
		case Apartment.TYPE_HOTEL:
			values[0] = Apartment.TYPE_APARTMENT;
		default:
			values[0] = Apartment.TYPE_HOTEL;
		}
		return orderDAO.getListByHQL(hql, values);
	}

	@Transactional
	@Override
	public List<Order> getCustomerOrders(Long cId, int type) {
		if (type == Apartment.TYPE_ALL) {
			Object[] values = new Object[1];
			values[0] = cId;
			return orderDAO.getListByHQL("from Order where cusId=?", values);
		}
		String hql = "from Order where cusId=? and type=?";
		Object[] values = new Object[2];
		values[0] = cId;
		switch (type) {
		case Apartment.TYPE_APARTMENT:
			values[1] = Apartment.TYPE_APARTMENT;
			break;
		case Apartment.TYPE_PLAY_ROOM:
			values[1] = Apartment.TYPE_PLAY_ROOM;
			break;
		default:
			values[1] = Apartment.TYPE_HOTEL;
		}

		return orderDAO.getListByHQL(hql, values);
	}

	@Transactional
	@Override
	public List<Order> checkAvailable(Long roomId, String startTime, String endTime) {
		String hql = "from Order where roomId = ? and (status =? or status=?) and (startTime >? and endTime <?)";
		Object[] values = { roomId, Order.STATUS_ON_PAY, Order.STATUS_ON_LEASE, (TimeUtil.getDateLong(startTime) - 1),
				(TimeUtil.getDateLong(endTime) + 1) };
		return orderDAO.getListByHQL(hql, values);
	}

	@Override
	public boolean isTimeOut(Order o) {
		Long time = o.getTime();
		Long deadLine = time + Constants.EFFECTIVE_ORDER_TIME_DURING;
		Long now = new Date().getTime();
		if (now > deadLine)
			return true;
		return false;
	}

	/**
	 * 条件查询订单
	 */
	@Transactional
	@Override
	public List<Order> search(Long cId, int category, int type, String startDate, String endDate, int range) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Order.class);
		c.add(Restrictions.eq("cusId", cId));

		if (type != 0) {// 不是 全部
			c.add(Restrictions.eq("type", type));
		}

		switch (category) {
		case 1:// 有效订单
			c.add(Restrictions.or(Restrictions.eq("status", Order.STATUS_COMPLETE),
					Restrictions.eq("status", Order.STATUS_ON_LEASE), Restrictions.eq("status", Order.STATUS_ON_PAY)));
			break;
		case 2:// 退款订单
			c.add(Restrictions.eq("status", Order.STATUS_CHARGEBACK));
			break;
		default:

		}
		c.add(Restrictions.between("time", TimeUtil.getDateLong(startDate),
				TimeUtil.getDateLong(endDate) + 1000 * 60 * 60 * 24));
		return c.list();

	}

	/**
	 * 更新所有订单
	 */
	@Transactional
	@Override
	public void refreshAll() {
		// TODO Auto-generated method stub
		 orderDAO.getListByHQL("from Order", null);
	}
	/**
	 * 
	 */
	@Transactional
	@Override
	public void refresh(Long cId) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Order.class);
		c.add(Restrictions.eq("cusId", cId));
		c.add(Restrictions.eq("status", Order.STATUS_ON_LEASE));
		List<Order> orders = c.list();
		long now = new Date().getTime();
		for(Order o:orders){
			//订单已完成
			if(o.getEndTime()<now){
				o.setStatus(Order.STATUS_COMPLETE);
				update(o);
			}
		}
	}
	
	
	public static void main(String[] args) {
		String str= "1";
		int len = str.length();
		StringBuffer sb = new StringBuffer();
		for(int i=len;i<5;i++){
			sb.append("0");
		}
		sb.append(str);
		System.out.println(sb.toString());
	}

	@Transactional
	@Override
	public Order getByPayNo(String payNo) {
		// TODO Auto-generated method stub
		return orderDAO.getByHQL("from Order where payNo="+payNo, null);
	}

}
