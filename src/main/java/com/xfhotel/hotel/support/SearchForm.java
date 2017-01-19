package com.xfhotel.hotel.support;

import java.util.Arrays;
/**
 * 搜索参数类
 * @author Ming
 *
 */
public class SearchForm {
	private String startTime;
	private String endTime;
	private Integer area;
	private Integer priceRange;
	private Integer layout;
	private Long[] features;
	private Integer enterTime;
	private Integer leaseType;
	
	private String moreStr;//模糊字段
	private Integer sortType;//0 推荐，1价格，2面积
	
	
	
	public String getMoreStr() {
		return moreStr;
	}
	public void setMoreStr(String moreStr) {
		this.moreStr = moreStr;
	}
	public Integer getSortType() {
		return sortType;
	}
	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}
	public Integer getLeaseType() {
		return leaseType;
	}
	public void setLeaseType(Integer leaseType) {
		this.leaseType = leaseType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getPriceRange() {
		return priceRange;
	}
	public void setPriceRange(Integer priceRange) {
		this.priceRange = priceRange;
	}
	public Integer getLayout() {
		return layout;
	}
	public void setLayout(Integer layout) {
		this.layout = layout;
	}
	public Long[] getFeatures() {
		return features;
	}
	public void setFeatures(Long[] features) {
		this.features = features;
	}
	public Integer getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Integer enterTime) {
		this.enterTime = enterTime;
	}
	public SearchForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public String toHttpGetPram(){
		StringBuffer sb = new StringBuffer();
		for(Long f:features){
			sb.append("&features="+f);
		}
		String fStr = sb.toString();
		return "startTime=" + startTime + "&endTime=" + endTime + "&area=" + area + "&priceRange="
				+ priceRange + "&layout=" + layout + fStr + "&enterTime="
				+ enterTime+"&leaseType="+leaseType+"&moreStr="+moreStr+"&sortType="+sortType;
	}
	@Override
	public String toString() {
		return "SearchForm [startTime=" + startTime + ", endTime=" + endTime + ", area=" + area + ", priceRange="
				+ priceRange + ", layout=" + layout + ", features=" + Arrays.toString(features) + ", enterTime="
				+ enterTime + ", leaseType=" + leaseType + ", moreStr=" + moreStr + ", sortType=" + sortType + "]";
	}
	
	
	
}
