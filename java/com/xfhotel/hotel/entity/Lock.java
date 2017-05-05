package com.xfhotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_lock")
public class Lock {
	
	@Id
	@GeneratedValue(generator = "lockgenerator")
	@GenericGenerator(name = "lockgenerator", strategy = "increment")
	private long id;
	//num of lock
	private String lock_no;
	//no of password
	private int pwd_no;
	//password
	private String pwd_text;
	//for verifying the pushing message
	private String business_id;
	//user name
	private String pwd_user_name;
	//user phone
	private String pwd_user_mobile;
	//user idcard
	private String pwd_user_idcard;
	//password valid start time
	private long valid_time_start;
	//password valid start end
	private long valid_time_end;
	//decription
	private String description;
	//workable?
	private int availiable;
	
	
	public int getAvailiable() {
		return availiable;
	}
	
	public void setAvailiable(int availiable) {
		this.availiable = availiable;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLock_no() {
		return lock_no;
	}
	public void setLock_no(String lock_no) {
		this.lock_no = lock_no;
	}
	public int getPwd_no() {
		return pwd_no;
	}
	public void setPwd_no(int pwd_no) {
		this.pwd_no = pwd_no;
	}
	public String getPwd_text() {
		return pwd_text;
	}
	public void setPwd_text(String pwd_text) {
		this.pwd_text = pwd_text;
	}
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public String getPwd_user_name() {
		return pwd_user_name;
	}
	public void setPwd_user_name(String pwd_user_name) {
		this.pwd_user_name = pwd_user_name;
	}
	public String getPwd_user_mobile() {
		return pwd_user_mobile;
	}
	public void setPwd_user_mobile(String pwd_user_mobile) {
		this.pwd_user_mobile = pwd_user_mobile;
	}
	public String getPwd_user_idcard() {
		return pwd_user_idcard;
	}
	public void setPwd_user_idcard(String pwd_user_idcard) {
		this.pwd_user_idcard = pwd_user_idcard;
	}
	public long getValid_time_start() {
		return valid_time_start;
	}
	public void setValid_time_start(long valid_time_start) {
		this.valid_time_start = valid_time_start;
	}
	public long getValid_time_end() {
		return valid_time_end;
	}
	public void setValid_time_end(long valid_time_end) {
		this.valid_time_end = valid_time_end;
	}
	
	
	
	
}
