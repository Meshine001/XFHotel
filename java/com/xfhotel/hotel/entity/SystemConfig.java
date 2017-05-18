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
