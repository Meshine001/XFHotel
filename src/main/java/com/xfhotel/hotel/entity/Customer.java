package com.xfhotel.hotel.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.support.DateUtil;

@Entity
@Table(name = "t_customer")
public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String tel;
	@JsonIgnore
	private String password;
	private int level;
	private int status;
	/**
	 * 注册时间
	 */
	private Long regTime;
	
	/**
	 * 消费次数
	 */
	private int consumptionTimes;
	
	/**
	 * 消费金额
	 */
	private Float consumptionCount;
	
	@OneToOne
	@JoinColumn(name = "details_id",unique = true)
	private CustomerDetails details;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Customer(String tel, String password) {
		super();
		this.tel = tel;
		this.password = password;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	

	public CustomerDetails getDetails() {
		return details;
	}


	public void setDetails(CustomerDetails details) {
		this.details = details;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public Long getRegTime() {
		return regTime;
	}


	public void setRegTime(Long regTime) {
		this.regTime = regTime;
	}


	public int getConsumptionTimes() {
		return consumptionTimes;
	}


	public void setConsumptionTimes(int consumptionTimes) {
		this.consumptionTimes = consumptionTimes;
	}


	public Float getConsumptionCount() {
		return consumptionCount;
	}


	public void setConsumptionCount(Float consumptionCount) {
		this.consumptionCount = consumptionCount;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Map toMap(){
		Map map = new HashMap();
		Date d = new Date(regTime);
		map.put("id", id);
		map.put("tel", tel);
		map.put("password", password);
		map.put("level", level);
		map.put("status", status);
		map.put("regTime", DateUtil.format(d));
		map.put("consumptionTimes", consumptionTimes);
		map.put("consumptionCount", consumptionCount);
		return map;
	}

}
