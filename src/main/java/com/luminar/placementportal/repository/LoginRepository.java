package com.luminar.placementportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luminar.placementportal.model.LoginModel;


@Repository
public interface LoginRepository extends JpaRepository<LoginModel,Long>{

	LoginModel getByUserName(String userName);
	
	Optional<LoginModel> findById(Long id);
	
}
