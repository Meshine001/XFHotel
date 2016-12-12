package com.xfhotel.hotel.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.xfhotel.hotel.common.Constants;

@Entity
@Table(name = "t_customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String tel;
	private String password;
	private int level;
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
	private Long consumptionCount;
	
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


	public int getId() {
		return id;
	}
	public void setId(int id) {
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


	public Long getConsumptionCount() {
		return consumptionCount;
	}


	public void setConsumptionCount(Long consumptionCount) {
		this.consumptionCount = consumptionCount;
	}

}
