package com.luminar.placementportal.repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

	Login getByUserName(String userName);
	Optional<Login> findById(Long id);
	//edited started--------------
	Optional<Login> findByUserName(String userName); 
	//edited ended-------------------------------
	

}
