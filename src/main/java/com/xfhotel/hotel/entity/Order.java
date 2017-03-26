package com.xfhotel.hotel.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.support.DateUtil;

@Entity
@Table(name = "t_order")
public class Order {
	public final static int CATEGOTY_ALL = 0;
	public final static int CATEGOTY_AVAILABLE = 1;
	public final static int CATEGOTY_CHARGEBACK = 2;
	
	
	public final static int STATUS_NOT_COMPLETE = 0;
	public final static int STATUS_ON_PAY = 1;
	public final static int STATUS_ON_LEASE = 2;//正在住
	public final static int STATUS_COMPLETE = 3;//
	public final static int STATUS_CANCEL = 4;
	public final static int STATUS_TIME_OUT = 5;
	public final static int STATUS_CHARGEBACK = 6;



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long cusId;// customerid
	private String description;
	private Long roomId;
	private String cusName;
	private String cusTel;
	private String cusIdCard;
	private String personal;
	private Long startTime;
	private Long endTime;
	private Long time;// 下单时间
	private int totalDay;
	private String price; //
	private String totalPrice;
	private String preferential;// 优惠
	private int type;// 订单种类
	private int status;// 订单状态
	private boolean needFapiao;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCusId() {
		return cusId;
	}

	public void setCusId(Long cusId) {
		this.cusId = cusId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusTel() {
		return cusTel;
	}

	public void setCusTel(String cusTel) {
		this.cusTel = cusTel;
	}

	public String getPersonal() {
		return personal;
	}

	public void setPersonal(String personal) {
		this.personal = personal;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public int getTotalDay() {
		return totalDay;
	}

	public void setTotalDay(int totalDay) {
		this.totalDay = totalDay;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPreferential() {
		return preferential;
	}

	public void setPreferential(String preferential) {
		this.preferential = preferential;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCusIdCard() {
		return cusIdCard;
	}

	public void setCusIdCard(String cusIdCard) {
		this.cusIdCard = cusIdCard;
	}

	public boolean isNeedFapiao() {
		return needFapiao;
	}

	public void setNeedFapiao(boolean needFapiao) {
		this.needFapiao = needFapiao;
	}

	public Map toMap(){
		Map info = new HashMap();
		info.put("id", id);
		info.put("cusId", cusId);
		info.put("description", description);
		info.put("roomId", roomId);
		info.put("cusName", cusName);
		info.put("cusTel", cusTel);
		info.put("cusIdCard", cusIdCard);
		info.put("personal", personal);
		info.put("startTime", DateUtil.format(new Date(startTime), "yyyy-MM-dd"));
		info.put("endTime", DateUtil.format(new Date(endTime), "yyyy-MM-dd"));
		info.put("time", time+Constants.EFFECTIVE_ORDER_TIME_DURING);
		info.put("timeStr", DateUtil.format(new Date(time), "yyyy-MM-dd HH:mm:ss"));
		info.put("totalDay", totalDay);
		info.put("price", price);
		info.put("totalPrice",totalPrice);
		info.put("preferential", preferential);
		info.put("type", getType(type));
		info.put("status", getStatusString(status));
		info.put("needFapiao", needFapiao?"有发票":"无发票");
		return info;
	}
	
	String getType(int type){
		switch (type) {
		case Apartment.TYPE_APARTMENT:
			return "公寓式";
		case Apartment.TYPE_HOTEL:
			return "酒店式";
		case Apartment.TYPE_PLAY_ROOM:
			return "休闲式";
		default:
			return "全部";
		}
	}

	String getStatusString(int status) {
		switch (status) {
		case STATUS_NOT_COMPLETE:
			return "未完成";
		case STATUS_ON_PAY:
			return "等待支付";
		case STATUS_CANCEL:
			return "已取消";
		case STATUS_TIME_OUT:
			return "订单超时";
		case STATUS_ON_LEASE:
			return "进行中";
		}
		return "已完成";
	}

}
