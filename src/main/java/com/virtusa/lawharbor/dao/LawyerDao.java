package com.virtusa.lawharbor.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virtusa.lawharbor.model.LawyerModel;


@Repository
public interface LawyerDao extends JpaRepository<LawyerModel, Integer> {
	
	public LawyerModel findByEmail(String email); 
	
	public LawyerModel findByEmailAndPassword(String email,String password);
	
	public boolean existsByEmail(String email);
	
	
	@Modifying
	@Transactional
	@Query(value = "update lawyer_model e set e.active = :active where e.email = :email", nativeQuery = true)
	public void updateActiveByEmail(@Param("active") Boolean active, @Param("email") String email);
}
