package com.xfhotel.hotel.support;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Apartment;
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
	
	public double rate(Map map){
		/**
		 * 对map对应apartment进行评分
		 */
		double r = 0;
//		private String startTime;
//		private String endTime;
		String location = (String) map.get("location");
		if( area==0 || Area.getAreaById(area).equals(location.split(",")[2])){
			r += 1;
		}
		if( priceRange.intValue() == 0 )
			r += 1;
		else{
			String[] prices = (String[]) map.get("prices");
			int price = Integer.valueOf(prices[0]);
			if( priceRange.intValue() == 1 ){
				if( price < Constants.price_scope[0])
					r +=1;
			}
			else if( priceRange.intValue() == Constants.price_scope.length+1 ){
				if( price > Constants.price_scope[Constants.price_scope.length-1])
					r +=1;
			}
			else{
				if( price > Constants.price_scope[priceRange-2] && price <= Constants.price_scope[priceRange-1])
					r +=1;
			}
		}
		int num_room = Integer.valueOf((String) map.get("bedroom"));
		if( layout.intValue()==0 )
			r += 1;
		else if( num_room>=layout.intValue() ){
			r += 1-(num_room-layout.intValue())/10.0;
		}
		if( features[0]==0)
			r +=1;
		else{
			double rt = 1;
			for(long id: features){
				List<Map> fea = (List<Map>) map.get("facilityEntity");
				int check = 0;
				for( Map f : fea){
					if( ((Long)f.get("id")).doubleValue() == id ){
						check = 1;
						break;
					}
				}
				if( check==0 ){
					rt /=2.0;
				}
			}
			r += rt;
		}
		String lt = (String) map.get("apartmentType");
		if( leaseType.intValue() == 0 )
			r += 1;
		else{
			if( lt.equals(Apartment.getTypeDescription(leaseType)) )
				r += 1;
		}
//		private String moreStr;//模糊字段
		return r;
	}
}
