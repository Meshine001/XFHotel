package com.xfhotel.hotel.support;

import java.util.ArrayList;
import java.util.List;

public class LayoutType {
	private int layoutId;
	private String layout;
	private static List<LayoutType> layouts;
	public LayoutType(int layoutId, String layout) {
		super();
		this.layoutId = layoutId;
		this.layout = layout;
	}
	public int getLayoutId() {
		return layoutId;
	}
	public void setLayoutId(int layoutId) {
		this.layoutId = layoutId;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public static List<LayoutType> getLayouts() {
		if(layouts == null){
			layouts = new ArrayList<LayoutType>();
			layouts.add(new LayoutType(0, "不限"));
			layouts.add(new LayoutType(1, "一居室"));
			layouts.add(new LayoutType(2, "二居室"));
			layouts.add(new LayoutType(3, "三居室"));
			layouts.add(new LayoutType(4, "四居室"));
		}
		return layouts;
	}
	
}
