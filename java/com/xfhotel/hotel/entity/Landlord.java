package com.xfhotel.hotel.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xfhotel.hotel.support.DateUtil;


@Entity
@Table(name = "t_landlord")
public class Landlord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long uId;//用户id
	private String name;//真实姓名
	private Long card;//身份证
	private String number;//账号
	private Long regTime;//注册时间
	private String phone;//电话号
	
	public Landlord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getuId() {
		return uId;
	}
	public void setuId(Long uId) {
		this.uId = uId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Long getRegTime() {
		return regTime;
	}
	public void setRegTime(Long l) {
		this.regTime = l;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCard() {
		return card;
	}
	public void setCard(Long card) {
		this.card = card;
	}
	public Map toMap(){
		Map map = new HashMap();
		map.put("id", id);
		map.put("uId", uId);
		map.put("name", name);
		map.put("card", card);
		map.put("number", number);
		map.put("regTime", DateUtil.format(new Date(regTime), "yyyy-MM-dd HH:mm:ss"));
		map.put("phone", phone);
		return map;
	}
	
}
