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
@Table(name = "t_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String username;
	private String password;
	private int authority;
	private Long date;
	private String contact;
	private int status;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public Map toMap(){
		Map info = new HashMap();
		info.put("id", id);
		info.put("username", username);
		info.put("authority", authority);
		info.put("time", DateUtil.format(new Date(date), "yyyy-MM-dd HH:mm:ss"));
		info.put("contact", contact);
		info.put("status", status);
		return info;
	}
}
