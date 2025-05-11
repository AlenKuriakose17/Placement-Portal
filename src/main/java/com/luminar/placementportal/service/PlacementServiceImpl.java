package com.luminar.placementportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luminar.placementportal.model.PlacementModel;
import com.luminar.placementportal.repository.PlacementRepository;

@Service

public class PlacementServiceImpl implements PlacementService {
	 
	
	@Autowired
	PlacementRepository placementRepository;
	
	@Override
	public List<PlacementModel> getAllPlacements(){
		return placementRepository.findAll();
	
	}
	
	@Override
	public void savePlacment(PlacementModel placement) {
		this.placementRepository.save(placement);
	
	}
	
	@Override
	public PlacementModel getPlacementById(long id) {
		
		Optional<PlacementModel> optional = placementRepository.findById(id);
		PlacementModel placement = null;
		
		if (optional.isPresent()) {
			placement = optional.get();
		} else {
			throw new RuntimeException(" User not found for id :: " + id);
		}
		
		return placement;
		
	}
	
	@Override
	public void deletePlacementById(long id) {
		this.placementRepository.deleteById(id);
	}

}
