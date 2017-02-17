package com.xfhotel.hotel.support;

import java.util.ArrayList;
import java.util.List;

import com.xfhotel.hotel.common.Constants;

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
			prices.add(new LeasePrice(0, "不限"));
			int len = Constants.price_scope.length;
			prices.add(new LeasePrice(1, Constants.price_scope[0]+"元以下"));
			for(int i=1;i<len;i++){
				prices.add(new LeasePrice(i+1, (Constants.price_scope[i-1]+1) + "-" + Constants.price_scope[i] + "元"));
			}
			prices.add(new LeasePrice(len+1, Constants.price_scope[len-1]+"元以上"));
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
