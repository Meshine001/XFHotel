package com.xfhotel.hotel.service;

import java.util.List;

import com.xfhotel.hotel.entity.Feature;

public interface FeatureService {
	public List<Feature> listFeatures();

	public Feature findById(Long id);
	public Feature add(String description);
	public void delete(Feature t);
	public void update(Feature t);
}
