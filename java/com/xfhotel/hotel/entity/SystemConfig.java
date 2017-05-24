package com.xfhotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_sysconf")
public class SystemConfig {
	@Id
	@GeneratedValue(generator="confgenerator")
	@GenericGenerator(name="confgenerator",strategy="increment")
	private long id;
	
	private Double ya_jin;
	
	private String sms;
	

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getYa_jin() {
		return ya_jin;
	}

	public void setYa_jin(Double ya_jin) {
		this.ya_jin = ya_jin;
	}
	
}
