package com.luminar.placementportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luminar.placementportal.model.PlacementModel;


@Repository
public interface PlacementRepository extends JpaRepository<PlacementModel,Long> {
	

	Optional<PlacementModel> findById(Long id);
	
}
