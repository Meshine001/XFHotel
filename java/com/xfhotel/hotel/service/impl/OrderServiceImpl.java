package com.xfhotel.hotel.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.dao.impl.OrderDAOImpl;
import com.xfhotel.hotel.entity.Apartment;
import com.xfhotel.hotel.entity.Coupon;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.Order;
import com.xfhotel.hotel.service.ApartmentService;
import com.xfhotel.hotel.service.CouponService;
import com.xfhotel.hotel.service.CustomerService;
import com.xfhotel.hotel.service.OrderService;
import com.xfhotel.hotel.service.SystemConfService;
import com.xfhotel.hotel.support.DateUtil;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.PageResults;
import com.xfhotel.hotel.support.StringSplitUtil;
import com.xfhotel.hotel.support.TimeUtil;
import com.xfhotel.hotel.support.sms.SendTemplateSMS;

import net.sf.json.JSONObject;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAOImpl orderDAO;
	
	@Autowired
	ApartmentService apartmentService;
	 
	@Autowired
	SystemConfService systemConfiService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CouponService couponService;

	@Autowired
	SessionFactory sessionFactory;

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
		String[] values = { Apartment.getTypeDescription(type)};
//		switch (type) {
//		case Apartment.TYPE_APARTMENT:
//			values[0] = Apartment.getTypeDescription(type);
//		case Apartment.TYPE_PLAY_ROOM:
//			values[0] = Apartment.TYPE_PLAY_ROOM;
//		default:
//			values[0] = Apartment.TYPE_HOTEL;
//		}

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
	public List<Order> checkAvailable(Long id, String startTime, String endTime) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Order.class);
		c.add(Restrictions.eq("roomId", id));
		SimpleExpression[] or = {Restrictions.eq("status", Order.STATUS_ON_PAY),
				Restrictions.eq("status", Order.STATUS_ON_LEASE),
				Restrictions.eq("status", Order.STATUS_ON_COMFIRM)};
		c.add(Restrictions.or(or));
		c.add(Restrictions.between("startTime", (TimeUtil.getDateLong(startTime) - 1), (TimeUtil.getDateLong(endTime) + 1)));
		return c.list();
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
			c.add(Restrictions.eq("type", Apartment.getTypeDescription(type)));
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
		List<Order> list = c.list();
		for(Order o:list){
			Long current = new Date().getTime();
			Long die = o.getTime()+Constants.EFFECTIVE_ORDER_TIME_DURING;
			if(die < current && o.getStatus() == Order.STATUS_ON_PAY ){
				o.setStatus(Order.STATUS_TIME_OUT);
				orderDAO.update(o);
			}
		}
		
		return list;

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
//	public static void main(String[] args) {
//		Long start = DateUtil.getStartTime();
//		Long end = DateUtil.getEndTime();
//		System.out.println(start);
//		System.out.println(end);
//		
//		System.out.println(DateUtil.format(new Date(start), "yyyy-MM-dd HH:mm:ss"));
//		
//		System.out.println(DateUtil.format(new Date(end), "yyyy-MM-dd HH:mm:ss"));
//		
////		String str= "1";
////		int len = str.length();
////		StringBuffer sb = new StringBuffer();
////		for(int i=len;i<5;i++){
////			sb.append("0");
////		}
////		sb.append(str);
////		System.out.println(sb.toString());
//	}
	@Transactional
	@Override
	public Order getByPayNo(String payNo) {
		// TODO Auto-generated method stub
		return orderDAO.getByHQL("from Order where payNo="+payNo, null);
	}

	@Transactional
	@Override
	public Order postOrder(Long cusId, String description, Long roomId, String cusName, String cusTel,
			String[] otherCusName, String[] otherCusIdCard, String cusIdCard, String personal, String startTime,
			String endTime, Integer totalDay, String price, String totalPrice, String preferential, boolean needFapiao,
			String apartmentType,Long counponId) {
		Customer c = customerService.getCustomer(cusId);
		
		if(null != counponId){
			Coupon coupon =couponService.getCoupon2(counponId);
			boolean isUsed = true;
			coupon.setUsed(isUsed);
			couponService.modify(coupon, counponId);
			double favorable = coupon.getcValue();
			Double totalPrice2 = Double.parseDouble(totalPrice);
			totalPrice = String.valueOf(totalPrice2 -favorable); 
		}
		if(c.getConsumptionCount()>=1000){
			Double totalPrice1 = Double.parseDouble(totalPrice);
			Double Price1 = Double.parseDouble(price);
			Double yj = totalPrice1-Price1;
			totalPrice = String.valueOf(totalPrice1 -yj); 
		}
		
		Order o = new Order();
		o.setCusId(cusId);
		o.setDescription(description);
		o.setRoomId(roomId);
		o.setCusName(cusName);
		o.setCusTel(cusTel); 
		o.setCusIdCard(cusIdCard);
		o.setPersonal(personal);
		o.setOtherCusName(StringSplitUtil.buildStrGroup(otherCusName));
		o.setOtherCusIdCard(StringSplitUtil.buildStrGroup(otherCusIdCard));
		try {
//			System.out.println(DateUtil.parse(startTime + " 14:00", "yyyy-MM-dd HH:mm").getTime());
			o.setStartTime(DateUtil.parse(startTime + " 14:00", "yyyy-MM-dd HH:mm").getTime());
			o.setEndTime(DateUtil.parse(endTime + " 12:00", "yyyy-MM-dd HH:mm").getTime());
//			System.out.println(DateUtil.parse(startTime + " 12:00", "yyyy-MM-dd HH:mm").getTime());
//			System.out.println(DateUtil.parse(endTime+ " 12:00", "yyyy-MM-dd HH:mm").getTime());
//			long i = DateUtil.parse(startTime + " 14:00", "yyyy-MM-dd HH:mm").getTime();
//			long d = DateUtil.parse(endTime+ " 12:00", "yyyy-MM-dd HH:mm").getTime();
//			SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
//			 System.out.println(sdf.format(new Date(i)));
//			 System.out.println(sdf.format(new Date(d)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		o.setTime(new Date().getTime());
		o.setTotalDay(totalDay);
		o.setPrice(price + "@" + systemConfiService.getConfig().getYa_jin());
		o.setTotalPrice(totalPrice);
		o.setPreferential(preferential);
		o.setType(apartmentType);
		o.setStatus(Order.STATUS_ON_PAY);
		o.setNeedFapiao(needFapiao);
		add(o);
		return o;
	}
	
	@Transactional
	@Override
	public Message outLease(Long id) {
		try {
			Order o = get(id);
			//TODO
			//发短信给管理员
			//【青舍都市】您有新订单需要确认，请及时处理。{1}
			JSONObject a = apartmentService.getApartmentById(o.getRoomId())
					.getJSONObject("position");
			String f= a.getString("xiao_qu")+","+a.getString("lou_hao")+"号楼,"+
					a.getString("dan_yuan")+"单元,"+a.getString("lou_ceng")+"层,"+a.getString("men_pai")+"号";
			String[] p = {f};
			SendTemplateSMS.sendSMS(Constants.SMS_INFORM_COMFIRM_ORDER, systemConfiService.getConfig().getSms(), p);
			o.setStatus(Order.STATUS_ON_OUT_LEASE);
			update(o);
			return new Message(Constants.MESSAGE_SUCCESS_CODE, "退租成功，等待管理员确认");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(Constants.MESSAGE_ERR_CODE, "退租失败，系统错误");
		}
		
	}
	@Transactional
	@Override
	public PageResults<Order> listPage(int page) {
		// TODO Auto-generated method stub
		return orderDAO.findPageByFetchedHql("from Order order by id desc", "select count(*) from Order", page, 10, null);
	}
	
	@Transactional
	@Override
	public List<Order> getOrders(Long roomId) {
		String hqlString = "from Order where roomId=? order by id desc";
		Object[] values = {roomId};
		return orderDAO.getListByHQL(hqlString, values);
	}


}
