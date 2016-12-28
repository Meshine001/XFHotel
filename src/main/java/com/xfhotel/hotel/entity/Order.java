package com.xfhotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_order")
public class Order {
	
	public final static int STATUS_NOT_COMPLETE = 0;
	public final static int STATUS_ON_PAY = 1;
	public final static int STATUS_COMPLETE = 2;
	public final static int STATUS_CANCEL = 3;
	
	public final static int TYPE_HOTEL = 0;
	public final static int TYPE_APARTMENT = 1;
	
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
	private Long time;//下单时间
	private int totalDay;
	private String price;
	private String totalPice;
	private String preferential;// 优惠
	private int type;// 订单种类
	private int status;// 订单状态

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

	public String getTotalPice() {
		return totalPice;
	}

	public void setTotalPice(String totalPice) {
		this.totalPice = totalPice;
	}


	public String getCusIdCard() {
		return cusIdCard;
	}


	public void setCusIdCard(String cusIdCard) {
		this.cusIdCard = cusIdCard;
	}

	

}
