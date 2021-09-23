package com.jwtAuth.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jwtAuth.model.Users;

public interface UserDaoRepository  extends JpaRepository<Users, Long> {
	
	@Query("from Users where email = :email")
	 Optional<Users> findByEmail(String email);
	    Boolean existsByEmail(String email);
}
