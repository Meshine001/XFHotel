package com.xfhotel.hotel.support;

import java.util.ArrayList;
import java.util.List;

public class LeasePrice {
	private int priceId;
	private String price;
	private static List<LeasePrice> prices=null;
	public LeasePrice(int priceId, String price) {
		super();
		this.priceId = priceId;
		this.price = price;
	}
	public int getPriceId() {
		return priceId;
	}
	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public static List<LeasePrice> getPrices() {
		if( prices == null){
			prices = new ArrayList<LeasePrice>();
			prices.add(new LeasePrice(0, "全部"));
			prices.add(new LeasePrice(1, "800元以下"));
			prices.add(new LeasePrice(2, "801-1000元"));
			prices.add(new LeasePrice(3, "1001-1500元"));
			prices.add(new LeasePrice(4, "1501-2000元"));
			prices.add(new LeasePrice(5, "2000元以上"));
		}
		return prices;
	}
	@Override
	public String toString() {
		return "LeasePrice [priceId=" + priceId + ", price=" + price + "]";
	}
	
	public static void main(String[] args) {
		System.out.println(LeasePrice.getPrices());
	}
}
