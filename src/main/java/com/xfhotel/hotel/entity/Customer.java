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
	private Long id;
	private String tel;
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


	

}
