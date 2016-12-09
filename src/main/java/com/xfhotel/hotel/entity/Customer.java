package com.xfhotel.hotel.entity;

import javax.persistence.CascadeType;
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



	
	
}
