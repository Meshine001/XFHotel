package com.xfhotel.hotel.support;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xfhotel.hotel.service.FeatureService;

public class Feature {
	private int featureId;
	private String feature;
	private static List<Feature> features;
	
	
	public Feature(int featureId, String feature) {
		super();
		this.featureId = featureId;
		this.feature = feature;
	}
	public int getFeatureId() {
		return featureId;
	}
	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}
	public String getfeature() {
		return feature;
	}
	public void setfeature(String feature) {
		this.feature = feature;
	}
	
	
	public static void setFeatures(List<Feature> features) {
		if(Feature.features == null){
			Feature.features = new ArrayList<Feature>();
			Feature.features.add(new Feature(0, "全部"));
		}else{
			for (Iterator iterator = features.iterator(); iterator.hasNext();) {
				Feature f = (Feature) iterator.next();
				if(f.getFeatureId() == 0)continue;
				iterator.remove();
			}
		}
		Feature.features.addAll(features);
	}
	public static List<Feature> getFeatures() {
		
		return features;
	}
	
	
}
