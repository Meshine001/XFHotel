package com.xfhotel.hotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_fitness")
public class Fitness {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String tel;
	private String name;
	private String price;
	private long merchant;
	private boolean situation;
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public long getMerchant() {
		return merchant;
	}
	public void setMerchant(long merchant) {
		this.merchant = merchant;
	}

	public boolean isSituation() {
		return situation;
	}
	public void setSituation(boolean situation) {
		this.situation = situation;
	}
	@Override
	public String toString() {
		return "Fitness [id=" + id + ", tel=" + tel + ", name=" + name + ", price=" + price + ", situation="
				+ situation + "]";
	}
}
