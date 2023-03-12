package com.virtusa.lawharbor.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virtusa.lawharbor.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
	public User findByEmailAndPassword(String email,String password);
	
	@Modifying
	@Transactional
	@Query(value = "update user e set e.active = :active where e.email = :email", nativeQuery = true)
	public void updateActiveByEmail(@Param("active") Boolean active, @Param("email") String email);
}
