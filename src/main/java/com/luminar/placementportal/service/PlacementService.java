package com.luminar.placementportal.service;

import java.util.List;

import com.luminar.placementportal.model.PlacementModel;

public interface PlacementService {
	
	List<PlacementModel>getAllPlacements();
	
	void savePlacment(PlacementModel placement);

	PlacementModel getPlacementById(long id);
	
	void deletePlacementById(long id);
}
